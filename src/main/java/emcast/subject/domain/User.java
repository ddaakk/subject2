package emcast.subject.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // 테이블 매핑
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // jpa에서 기본키 auto increment 생성
    private Long id;

    private String name;

    public User(String name) {
        this.name = name;
    }
}
