package ru.stepanov.skypro.coursework.examwebapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
                reason = "Same question is in the list")
public class QuestionIsAlreadyAdded extends RuntimeException {
    public QuestionIsAlreadyAdded() {
    }
}
