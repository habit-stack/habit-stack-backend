package com.es.habitstack.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {



    /**
     * 직접 정의한 비즈니스 예외(BaseException)를 처리
     * @param e BaseException 또는 그 하위 타입의 예외 객체
     * @return ErrorCode에 정의된 HTTP 상태 코드와 에러 메시지를 담은 ResponseEntity
     */
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {


        return ResponseEntity
                .status(e.getHttpStatus()) //예외 객체(e)에 저장 된 HTTP상태 코드를 가져옵니다. (예 : 404 NOT_FOUND
                .body(ErrorResponse.from(e.getErrorCode())); //예외 객체(e) ErrorCode로 에러 응답 DTO를 생성합니다.

    }
}
