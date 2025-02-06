package emcast.subject.dto.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private String userName;
    private List<OrderInfo> orders;
}
