package emcast.subject.controller;

import emcast.subject.dto.ApiResponse;
import emcast.subject.dto.service.OrderDto;
import emcast.subject.dto.controller.OrderRequest;
import emcast.subject.dto.service.OrderResponse;
import emcast.subject.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping( "/order")
    public ApiResponse<Long> saveOrder(@Valid @RequestBody OrderRequest request) {
        OrderDto orderInfo = new OrderDto(request.getUsername(), request.getOrderItemRequests());
        Long orderId = orderService.saveOrder(orderInfo);
        return ApiResponse.success(orderId);
    }

    //주문 조회
    @GetMapping("/orders")
    public ApiResponse<OrderResponse> getOrders(@RequestParam String username) {
        OrderResponse orders = orderService.getOrders(username);
        return ApiResponse.success(orders);
    }

    // 주문 배송중으로 상태 변경
    @PutMapping("/order/ship")
    public ApiResponse<Long> shipOrder(@RequestParam Long id) {
        Long shipOrder = orderService.shipOrder(id);
        return ApiResponse.success(shipOrder);
    }

    // 주문 취소
    @PutMapping("/order/cancel")
    public ApiResponse<Long> cancelOrder(@RequestParam Long id) {
        Long cancelOrder = orderService.cancelOrder(id);
        return ApiResponse.success(cancelOrder);
    }
}
