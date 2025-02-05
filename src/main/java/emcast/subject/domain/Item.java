package emcast.subject.domain;

import emcast.subject.exception.CommonException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Integer stock;

    public Item(String name, Long price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void decreaseStock(Integer decreaseStock) {
        Integer restStock = this.stock - decreaseStock;
        if (restStock < 0) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "재고가 부족합니다.");
        }
        this.stock = restStock;
    }

    public void rollbackStock(Integer count) {
        this.stock += count;
    }
}
