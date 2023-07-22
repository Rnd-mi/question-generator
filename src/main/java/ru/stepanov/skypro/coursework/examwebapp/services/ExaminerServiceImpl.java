package ru.stepanov.skypro.coursework.examwebapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.InvalidAmountException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Map<String, QuestionService> services = new HashMap<>();
    private final Random random;

    @Autowired
    public ExaminerServiceImpl(QuestionService mathQuestionService,
                               QuestionService javaQuestionService) {
        services.put("math", mathQuestionService);
        services.put("java", javaQuestionService);
        random = new Random();
    }

    public ExaminerServiceImpl(QuestionService mathQuestionService,
                               QuestionService javaQuestionService,
                               Random random) {
        services.put("math", mathQuestionService);
        services.put("java", javaQuestionService);
        this.random = random;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        amountCheck(amount);
        Set<Question> set = new LinkedHashSet<>();
        int javaCounter = random.nextInt(javaSize() + 1);

        if (javaSize() >= amount) {
            javaCounter = random.nextInt(amount + 1);
        }
        int mathCounter = amount - javaCounter;

        while (mathCounter > 0) {
            if (set.add(services.get("math").getRandomQuestion())) {
                mathCounter--;
            }
        }

        while (javaCounter > 0) {
            if (set.add(services.get("java").getRandomQuestion())) {
                javaCounter--;
            }
        }
        return set;
    }

    private void amountCheck(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
    }

    private int javaSize() {
        return services.get("java").getAll().size();
    }
}
