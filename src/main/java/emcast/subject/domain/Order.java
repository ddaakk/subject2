package emcast.subject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalPrice;

    private Long userId;


    @Enumerated(value = EnumType.STRING) // 문자열로 저장
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // order table 매핑, 모든 작업 전파
    private List<OrderItem> orderItems = new ArrayList<>();

    @Builder
    public Order (List<Item> items, Long id, OrderStatus status, Long userId, Long totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.status = status;
        this.orderItems = items.stream()
                .map(item -> new OrderItem(this, item))
                .toList();
    }
}
