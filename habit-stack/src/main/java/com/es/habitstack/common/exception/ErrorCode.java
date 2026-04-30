package com.es.habitstack.common.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {



    /* 400 - Bad Request */
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "B001", "입력 값이 올바르지 않습니다"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "B002", "유효하지 않은 토큰입니다."),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "B003", "입력값 유효성 검사에 실패했습니다."),
    AUTHENTICATION_FAILED(HttpStatus.BAD_REQUEST, "B004", "인증에 실패했습니다."),


    /* 401 - Unauthorized */
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "U001", "Access Token이 만료되었습니다. 토큰을 재발급해주세요"),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED,"U002","권한이 없습니다"),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED,"U004","유효하지 않는 토큰입니다"),
    REFRESH_TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED,"U005","리프레쉬 토큰이 불일치 합니다"),

    /* 403 - Forbidden */
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "F001", "해당 리소스에 접근할 권한이 없습니다"),
    ADMIN_ONLY(HttpStatus.FORBIDDEN, "F002", "관리자 권한이 필요합니다"),
    MEMBER_DEACTIVATED(HttpStatus.FORBIDDEN,"F003","비활성화된 멤버입니다"),

    /* 404 - Not Found */
    MEMEBER_NOT_FOUND(HttpStatus.NOT_FOUND,"NF001","해당 사용자를 찾을 수 없습니다"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,"NF002","해당 카테고리를 찾을 수 없습니다"),
    RECORD_NOT_FOUND(HttpStatus.NOT_FOUND,"NF003","해당 기록을 찾을 수 없습니다"),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND,"NF004","헤당 카테고리를 찾을 수 없습니다"),

    /* 500 - Internal Server Error */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SE001", "서버에 오류가 발생했습니다."), ;




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
