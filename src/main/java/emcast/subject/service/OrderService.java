package emcast.subject.service;

import emcast.subject.domain.*;
import emcast.subject.dto.service.*;
import emcast.subject.exception.CommonException;
import emcast.subject.repository.OrderItemRepository;
import emcast.subject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final UserService userService;
    private final ItemService itemService;


    @Transactional
    public Long saveOrder(OrderDto orderDto) {

        User user = userService.getUserInfo(orderDto.getUserName());
        // DB에 있는 아이템 가져옴
        List<Item> items = itemService.getItemList(orderDto.getOrderItemRequests());

        // 1. orderItem 생성, orderItem 에 item 정보 넣기
        List<OrderItem> orderItems = items.stream().map(item -> {
            Integer stock = orderDto.getOrderItemRequests().stream()
                    .filter(dto -> item.getId().equals(dto.getItemId()))
                    .findFirst()
                    .get()
                    .getStock();

            return OrderItem.createOrderItem(item, stock);
        }).toList();

        orderItemRepository.saveAll(orderItems);


        // 2. order 생성 , orderItem list 넣기
        Order newOrder = Order.createOrder(orderItems, user.getId());

        // 3. orderItem에 order 정보 넣기
        orderItems.stream().forEach(o -> o.updateOrder(newOrder));

        // 4. db 저장
        orderRepository.save(newOrder);

        return newOrder.getId();
    }

    public OrderResponse getOrders(String name){
        User user = userService.getUserInfo(name);
        List<Order> userOrders = orderRepository.findAllByUserId(user.getId());
        if (userOrders.isEmpty()) {
            throw new CommonException(HttpStatus.NO_CONTENT, "회원의 주문 내역이 없습니다.");
        }

        List<OrderInfo> orderInfos = new ArrayList<>();
        for (var order : userOrders) {
            List<OrderItemInfo> list = order.getOrderItems().stream()
                    .map(oi -> new OrderItemInfo(oi.getItem().getName(), oi.getPrice(), oi.getStock()))
                    .toList();

            orderInfos.add(new OrderInfo(list, order.getTotalPrice(), order.getStatus()));
        }

        return new OrderResponse(user.getName(), orderInfos);
    }

    @Transactional
    public Long shipOrder(Long id){
        Order foundOrder = getOrderById(id);
        foundOrder.shipOrder();
        return foundOrder.getId();
    }

    @Transactional
    public Long cancelOrder(Long id){
        Order foundOrder = getOrderById(id);
        foundOrder.cancelOrder();
        return foundOrder.getId();
    }

    private Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new CommonException(HttpStatus.BAD_REQUEST, "해당하는 주문이 없습니다."));
    }
}
