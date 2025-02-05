package emcast.subject.dto.service;

import emcast.subject.domain.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {

    private String username;
    private List<OrderItem> orderItems;
    private Long price;
}
