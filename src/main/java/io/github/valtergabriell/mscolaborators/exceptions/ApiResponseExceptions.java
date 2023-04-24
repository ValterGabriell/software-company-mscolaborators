package io.github.valtergabriell.mscolaborators.exceptions;

import org.springframework.http.HttpStatus;

public record ApiResponseExceptions(String message, HttpStatus httpStatus) { }
