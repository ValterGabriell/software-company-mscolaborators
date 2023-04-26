package io.github.valtergabriell.mscolaborators.application.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Response<T> {
    private T data;
    private String message;

}
