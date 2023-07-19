package ru.stepanov.skypro.coursework.examwebapp.services;

import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
