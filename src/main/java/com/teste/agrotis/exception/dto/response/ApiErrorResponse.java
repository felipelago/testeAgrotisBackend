package com.teste.agrotis.exception.dto.response;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        String message,
        LocalDateTime timeStamp
) {
}
