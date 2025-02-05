package emcast.subject.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "product")
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private Long stock;

}
