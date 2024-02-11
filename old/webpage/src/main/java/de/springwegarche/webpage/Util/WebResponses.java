package de.springwegarche.webpage.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class WebResponses {

    public static ResponseEntity badResponse(@Nullable String body) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(body);
    }
    public static ResponseEntity okResponse(@Nullable String body) {
        return ResponseEntity.status(HttpStatus.OK.value()).body(body);
    }
    public static ResponseEntity unauthorizedResponse(@Nullable String body) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(body);
    }
    public static ResponseEntity internalServerErrorResponse(@Nullable String body) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(body);
    }
    public static ResponseEntity conflictResponse(@Nullable String body) {
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(body);
    }
}
