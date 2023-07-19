package ru.stepanov.skypro.coursework.examwebapp.services;

import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.InvalidAmountException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        amountCheck(amount);
        Set<Question> set = new HashSet<>();

        while (amount > 0) {
            Question question = questionService.getRandomQuestion();

            if (set.add(question)) {
                amount--;
            }
        }
        return set;
    }

    private void amountCheck(int amount) {

        if (amount > questionService.getAll().size()
                || amount <= 0) {
            throw new InvalidAmountException();
        }
    }
}
