package emcast.subject.dto.controller;

import emcast.subject.dto.service.OrderItemRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String username;

    @NotNull(message = "주문 상품을 선택해주세요.")
    private List<OrderItemRequest> orderItemRequests;
}
