package emcast.subject.service;

import emcast.subject.domain.Item;
import emcast.subject.domain.Order;
import emcast.subject.domain.OrderStatus;
import emcast.subject.domain.User;
import emcast.subject.dto.service.OrderItemInfo;
import emcast.subject.dto.service.OrderResponse;
import emcast.subject.exception.CommonException;
import emcast.subject.repository.ItemRepository;
import emcast.subject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    private final UserService userService;

    @Transactional
    public Long saveOrder(String username, List<OrderItemInfo> orderItemInfos) {

        User user = userService.getUserInfo(username);
        List<Item> items = getRepositoryItems(orderItemInfos);

        // 총 가격 계산
        Long totalPrice = getTotalPrice(items, orderItemInfos);


        // 주문 생성
        Order newOrder = Order.builder()
                .items(items)
                .status(OrderStatus.INIT)
                .userId(user.getId())
                .totalPrice(totalPrice)
                .build();

        // db 저장
        orderRepository.save(newOrder);

        return newOrder.getId();
    }

    public List<Item> getRepositoryItems(List<OrderItemInfo> orderItemInfos) {
        List<Long> itemIds = orderItemInfos.stream()
                .map(OrderItemInfo::getItemId)
                .toList();

        List<Item> items = itemRepository.findAllById(itemIds);
        if (items.isEmpty()) {
            throw new CommonException(HttpStatus.FORBIDDEN, "일치하는 상품이 존재하지 않습니다.");
        }
        return items;
    }

    public Long getTotalPrice(List<Item> items, List<OrderItemInfo> orderItemInfos) {
        Long totalPrice = 0L;

        for (OrderItemInfo orderItemInfo : orderItemInfos) {
            Long price = items.stream()
                    .filter(item -> item.getId().equals(orderItemInfo.getItemId()))
                    .findFirst().get().getPrice();
            totalPrice += price * orderItemInfo.getStock();
        }

        return totalPrice;
    }
}
