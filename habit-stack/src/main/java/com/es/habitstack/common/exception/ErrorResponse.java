package com.es.habitstack.common.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private final String message;
    private final String code;

    public static ErrorResponse from(ErrorCode errorCode) {

        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

}
