package emcast.subject.domain;

import emcast.subject.exception.CommonException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public static Order createOrder(List<OrderItem> orderItems, Long userId) {
        return Order.builder()
                .orderItems(orderItems)
                .totalPrice(getTotalPrice(orderItems))
                .userId(userId)
                .status(OrderStatus.COMPLETED)
                .build();
    }

    public static Long getTotalPrice(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToLong(oi -> oi.getPrice() * oi.getStock()).sum();
    }

    public void shipOrder() {
        if (!status.equals(OrderStatus.COMPLETED)) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "배송을 시작할 수 없습니다.");
        }
        this.status = OrderStatus.SHIPPING;
    }

    public void cancelOrder() {
        if (!status.equals(OrderStatus.COMPLETED)) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "주문을 취소할 수 없습니다.");
        }
        this.status = OrderStatus.CANCEL;

        // 재고 원복
        orderItems.stream().forEach(oi -> {
            Item item = oi.getItem();
            item.rollbackStock(oi.getStock());
        });
    }
}
