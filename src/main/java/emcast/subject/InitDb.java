package emcast.subject;

import emcast.subject.domain.Item;
import emcast.subject.domain.User;
import emcast.subject.repository.ItemRepository;
import emcast.subject.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    @PostConstruct
    public void setUp() {
        // user
        userRepository.save(new User("test"));

        // item
        List<Item> items = List.of(new Item("item1", 1000L, 10),
                new Item("item2", 2000L, 10),
                new Item("item3", 3000L, 10),
                new Item("item4", 4000L, 10),
                new Item("item5", 5000L, 10),
                new Item("item6", 6000L, 10)
        );
        itemRepository.saveAll(items);
    }


}
