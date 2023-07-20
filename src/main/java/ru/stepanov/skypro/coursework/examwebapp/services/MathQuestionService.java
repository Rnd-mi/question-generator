package ru.stepanov.skypro.coursework.examwebapp.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionIsAlreadyAdded;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionNotFoundException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.repositories.QuestionRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    private final QuestionRepository rep;
    private final Random random = new Random();

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository rep) {
        this.rep = rep;
    }

    @Override
    public Question add(String q, String a) {
        Question question = new Question(q, a);
        return add(question);
    }

    @Override
    public Question add(Question question) {
        if (rep.getAll().contains(question)) {
            throw new QuestionIsAlreadyAdded();
        }

        return rep.add(question);
    }

    @Override
    public Question remove(Question question) {
        if (rep.getAll().contains(question)) {
            return rep.remove(question);
        }

        throw new QuestionNotFoundException();
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(rep.getAll());
    }

    @Override
    public Question getRandomQuestion() {
        return rep.getAll().stream()
                .skip(random.nextInt(rep.getAll().size()))
                .findFirst()
                .get();
    }
}
