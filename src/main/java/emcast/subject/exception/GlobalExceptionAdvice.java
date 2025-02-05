package emcast.subject.exception;

import emcast.subject.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionAdvice {

    @ExceptionHandler
    public ApiResponse handleCommonException(CommonException e) {
        return ApiResponse.fail(e.getMessage(), e.getHttpStatus());
    }
}
