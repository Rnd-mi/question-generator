package ru.stepanov.skypro.coursework.examwebapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,
                reason = "Question has not been found")
public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
    }
}
