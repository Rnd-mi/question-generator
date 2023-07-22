package ru.stepanov.skypro.coursework.examwebapp.services.math;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.MethodNotAllowedException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.services.QuestionService;

import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final Random random;
    private final List<MathOperation> operations;

    @Autowired
    public MathQuestionService(List<MathOperation> operations) {
        random = new Random();
        this.operations = operations;
    }

    public MathQuestionService(Random random, List<MathOperation> operations) {
        this.random = random;
        this.operations = operations;
    }

    @Override
    public Question add(String q, String a) {
        throw new MethodNotAllowedException();
    }

    @Override
    public Question add(Question question) {
        throw new MethodNotAllowedException();
    }

    @Override
    public Question remove(Question question) {
        throw new MethodNotAllowedException();
    }

    @Override
    public Collection<Question> getAll() {
        throw new MethodNotAllowedException();
    }

    @Override
    public Question getRandomQuestion() {
        int operation = random.nextInt(4);
        return operations.get(operation).generateQuestion();
    }
}
