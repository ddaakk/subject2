package emcast.subject.dto.service;

import emcast.subject.domain.Order;
import emcast.subject.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private String userName;
    private List<OrderInfo> orders;
}
