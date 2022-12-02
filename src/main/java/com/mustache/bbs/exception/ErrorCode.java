package com.mustache.bbs.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATE_USER(HttpStatus.CONFLICT, "duplicated User"),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "not found User"),
    ERROR_INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server error"),
    INVALID_ID_PASSWORD(HttpStatus.BAD_REQUEST, "Bad Request");

    private HttpStatus httpStatus;
    private String message;
}
