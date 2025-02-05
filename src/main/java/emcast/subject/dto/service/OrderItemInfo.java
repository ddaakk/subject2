package emcast.subject.dto.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemInfo {

    private Long itemId;
    private Integer stock;

    public OrderItemInfo(Long itemId, Integer stock) {
        this.itemId = itemId;
        this.stock = stock;
    }
}
