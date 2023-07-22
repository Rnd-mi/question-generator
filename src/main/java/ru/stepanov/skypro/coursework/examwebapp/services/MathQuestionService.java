package ru.stepanov.skypro.coursework.examwebapp.services;

import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.InvalidAmountException;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.MethodNotAllowedException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final Random random;
    private static final int BOUND_0_1 = 1001;
    private static final int BOUND_2_3 = 21;
    private static final int BOUND_4 = 10;
    private static final int OPERATION = 4;

    public MathQuestionService() {
        random = new Random();
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
        int operation = random.nextInt(OPERATION);

        switch (operation) {
            case 0:
                return addition(random.nextInt(BOUND_0_1), random.nextInt(BOUND_0_1));
            case 1:
                return subtraction(random.nextInt(BOUND_0_1), random.nextInt(BOUND_0_1));
            case 2:
                return multiplication(random.nextInt(BOUND_2_3), random.nextInt(BOUND_2_3));
            case 3:
                return division(random.nextInt(BOUND_2_3), random.nextInt(BOUND_4) + 1);
        }
        throw new InvalidAmountException();
    }

    public Question addition(int num1, int num2) {
        return new Question(num1 + " + " + num2 + " = ? ",
                Integer.toString((num1 + num2)));
    }

    public Question subtraction(int num1, int num2) {
        return new Question(num1 + " - " + num2 + " = ? ",
                Integer.toString((num1 - num2)));
    }

    public Question multiplication(int num1, int num2) {
        return new Question(num1 + " * " + num2 + " = ? ",
                Integer.toString((num1 * num2)));
    }

    public Question division(int num1, int num2) {
        double result = num1 / (double) num2;
        String str = String.format(Double.toString(result), "%.2f");

        return new Question(num1 + " / " + num2 + " = ? ", str);
    }
}
