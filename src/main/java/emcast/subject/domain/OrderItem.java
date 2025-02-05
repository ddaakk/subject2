package emcast.subject.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public OrderItem(Item item, Integer stock) {
        this.item = item;
        this.stock = stock;
        this.price = item.getPrice();
    }

    public static OrderItem createOrderItem(Item item, Integer stock) {
        OrderItem orderItem = new OrderItem(item, stock);
        item.decreaseStock(stock);
        return orderItem;
    }

    public void updateOrder(Order order) {
        this.order = order;
    }
}
