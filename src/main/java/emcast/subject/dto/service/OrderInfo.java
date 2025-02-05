package emcast.subject.dto.service;

import emcast.subject.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderInfo {

    private List<OrderItemInfo> orderItemInfos;
    private Long totalPrice;
    private OrderStatus status;
}
