package emcast.subject.service;

import emcast.subject.domain.User;
import emcast.subject.exception.CommonException;
import emcast.subject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserInfo(String userName) {
        return userRepository.findByName(userName)
                .orElseThrow(() -> new CommonException(HttpStatus.BAD_REQUEST, "회원정보가 없습니다."));
    }
}
