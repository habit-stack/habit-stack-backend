package com.es.habitstack.common.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@RequiredArgsConstructor
@Getter
public class BaseException extends RuntimeException {

    public static final BaseException INVALID_INPUT_VALUE = new BaseException(ErrorCode.INVALID_INPUT_VALUE);
    public static final BaseException INVALID_TOKEN = new  BaseException(ErrorCode.INVALID_TOKEN);
    public static final BaseException VALIDATION_FAILED = new BaseException(ErrorCode.VALIDATION_FAILED);
    public static final BaseException AUTHENTICATION_FAILED = new BaseException(ErrorCode.AUTHENTICATION_FAILED);
    public static final BaseException EXPIRED_ACCESS_TOKEN = new BaseException(ErrorCode.EXPIRED_ACCESS_TOKEN);
    public static final BaseException ACCESS_DENIED = new BaseException(ErrorCode.ACCESS_DENIED);
    public static final BaseException UNAUTHORIZED_ACCESS = new  BaseException(ErrorCode.UNAUTHORIZED_ACCESS);
    public static final BaseException INVALID_REFRESH_TOKEN = new BaseException(ErrorCode.INVALID_REFRESH_TOKEN);
    public static final BaseException REFRESH_TOKEN_MISMATCH = new BaseException(ErrorCode.REFRESH_TOKEN_MISMATCH);
    public static final BaseException ADMIN_ONLY = new BaseException(ErrorCode.ADMIN_ONLY);
    public static final BaseException MEMBER_DEACTIVATED = new BaseException(ErrorCode.MEMBER_DEACTIVATED);
    public static final BaseException MEMBER_NOT_FOUND = new BaseException(ErrorCode.MEMEBER_NOT_FOUND);


    private final ErrorCode errorCode;


    /**
     * ErrorCode와 사용자 정의 메시지를 함께 전달받는 생성자
     *
     * 기본 ErrorCode에 정의된 메시지 외에 상황에 맞는 상세 메시지를 지정할 수 있으며,
     * 예외 발생 시 해당 정보를 로그로 기록한다.
     *
     * @param errorCode 에러 코드 (HTTP 상태, 코드 값, 기본 메시지 포함)
     * @param message   상황에 맞는 커스텀 에러 메시지
     */
    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        log.error("[BaseException] {} ==> {}",errorCode.getCode(),message);
    }


    /**
     * 스택 트레이스 생성을 생략하여 성능을 최적화한다.
     * 기본 RuntimeException은 예외 발생 시 호출 경로를 추적하지만,
     * 본 프로젝트에서는 ErrorCode와 로그로 충분히 추적 가능하므로 생략한다.
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    //ErrorCode -> HTTP응답 코드로 변환하는 연결 메소드
    public HttpStatus getHttpStatus() {return errorCode.getHttpStatus();}
}
