package emcast.subject.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {

    private String username;
    private List<OrderItemRequest> orderItemRequests;
}
