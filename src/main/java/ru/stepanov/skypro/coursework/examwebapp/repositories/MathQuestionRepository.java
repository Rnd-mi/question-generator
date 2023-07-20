package ru.stepanov.skypro.coursework.examwebapp.repositories;

import org.springframework.stereotype.Repository;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> rep = new HashSet<>();

    public MathQuestionRepository() {
    }

    @PostConstruct
    public void init() {
        add(new Question("Put missing numbers: 1, ..., 7, ..., 13, ..., 19", "4, 10, 16"));
        add(new Question("Type 3 section of Mathematics", "algebra, geometry, arithmetic"));
        add(new Question("Calculate the result of 6^3", "216"));
        add(new Question("Definition of right triangle",
                "Triangle with one of its angles equal to 90 degrees"));
    }

    @Override
    public Question add(Question question) {
        rep.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        rep.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return rep;
    }
}
