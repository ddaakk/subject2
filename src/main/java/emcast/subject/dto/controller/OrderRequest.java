package emcast.subject.dto.controller;

import emcast.subject.dto.service.OrderItemInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequest {

    private String username;
    private List<OrderItemInfo> orderItemInfos;
}
