package ru.stepanov.skypro.coursework.examwebapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
                reason = "Amount should be positive and less than number of questions")
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
    }
}
