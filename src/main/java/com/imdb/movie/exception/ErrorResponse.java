package com.imdb.movie.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResponse {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private Map<String, String> errors;
}
