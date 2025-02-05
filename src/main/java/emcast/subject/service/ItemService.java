package emcast.subject.service;

import emcast.subject.domain.Item;
import emcast.subject.dto.service.OrderItemRequest;
import emcast.subject.exception.CommonException;
import emcast.subject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true) // select를 명시적으로 알기위해 작성 , jpa 영속성 context를 사용하지 않음
    public List<Item> getItemList(List<OrderItemRequest> orderItemRequests) {
        List<Long> itemIds = orderItemRequests.stream()
                .map(OrderItemRequest::getItemId)
                .toList();

        List<Item> items = itemRepository.findAllById(itemIds);
        if (items.isEmpty()) {
            throw new CommonException(HttpStatus.BAD_REQUEST, "일치하는 상품이 존재하지 않습니다.");
        }

        return items;
    }
}
