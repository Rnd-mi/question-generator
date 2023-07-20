package ru.stepanov.skypro.coursework.examwebapp.services;

import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.InvalidAmountException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService mathQuestionService;
    private final QuestionService javaQuestionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(QuestionService mathQuestionService,
                               QuestionService javaQuestionService) {
        this.mathQuestionService = mathQuestionService;
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        amountCheck(amount);
        Set<Question> set = new HashSet<>();
        int mathCounter = random.nextInt(mathSize() + 1);

        if (amount <= mathSize()) {
            mathCounter = random.nextInt(amount + 1);
        }
        int javaCounter = amount - mathCounter;

        while (javaCounter > javaSize()) {
            mathCounter++;
            javaCounter--;
        }

        while (mathCounter > 0) {
            if (set.add(mathQuestionService.getRandomQuestion())) {
                mathCounter--;
            }
        }

        while (javaCounter > 0) {
            if (set.add(javaQuestionService.getRandomQuestion())) {
                javaCounter--;
            }
        }
        return set;
    }

    private void amountCheck(int amount) {
        int acceptableSize = javaSize() + mathSize();

        if (amount > acceptableSize || amount <= 0) {
            throw new InvalidAmountException();
        }
    }

    private int mathSize() {
        return mathQuestionService.getAll().size();
    }

    private int javaSize() {
        return javaQuestionService.getAll().size();
    }
}
