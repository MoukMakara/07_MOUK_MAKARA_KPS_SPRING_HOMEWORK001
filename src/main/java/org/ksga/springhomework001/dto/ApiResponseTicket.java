package org.ksga.springhomework001.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseTicket<T> {
    private boolean success;
    private String message;
    private HttpStatus status;
    private T payload;
    private String timestamp;

    public static <T> ApiResponseTicket<T> createSuccessResponse(String message, T payload) {
        return new ApiResponseTicket<>(true, message, HttpStatus.OK, payload, LocalDate.now().toString());
    }

    public static <T> ApiResponseTicket<T> createErrorResponse(String message, HttpStatus status) {
        return new ApiResponseTicket<>(false, message, status, null, LocalDate.now().toString());
    }
}
