package com.example.homework.exception;

public record ExceptionDto(String message, String exception, ServiceErrorCode errorCode) {
}
