package emcast.subject.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OrderStatus {

    INIT,
    COMPLETED,
    SHIPPING,
    CANCEL
}
