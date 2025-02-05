package emcast.subject.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemRequest {

    private Long itemId;
    private Integer stock;
}
