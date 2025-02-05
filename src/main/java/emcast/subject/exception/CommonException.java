package emcast.subject.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CommonException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public CommonException() {}
}
