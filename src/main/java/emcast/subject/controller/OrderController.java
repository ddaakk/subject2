package emcast.subject.controller;

import emcast.subject.dto.ApiResponse;
import emcast.subject.dto.service.OrderItemInfo;
import emcast.subject.dto.controller.OrderRequest;
import emcast.subject.dto.service.OrderResponse;
import emcast.subject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 생성 > 주문 확인 상태
    @PostMapping( "/order")
    public ApiResponse<Long> saveOrder(@RequestParam OrderRequest request) {
        String username = request.getUsername();
        List<OrderItemInfo> orderItemInfos = request.getOrderItemInfos();
        Long orderId = orderService.saveOrder(username, orderItemInfos);
        return ApiResponse.success(orderId);
    }

    // 주문 조회

    // 주문 재고 체크 > 주문 완료로 상태 변경

    // 주문 취소
}
