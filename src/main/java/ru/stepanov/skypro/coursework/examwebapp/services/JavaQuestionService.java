package ru.stepanov.skypro.coursework.examwebapp.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionNotFoundException;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionIsAlreadyAddedException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.repositories.QuestionRepository;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository rep;
    private final Random random = new Random();

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository rep) {
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
            throw new QuestionIsAlreadyAddedException();
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
//        int left = 0;
//        int right = random.nextInt(rep.size());
//
//        for (Question question : rep) {
//
//            if (left == right) {
//                return question;
//            }
//            left++;
//        }
//        throw new RuntimeException();

        return rep.getAll().stream()
                .skip(random.nextInt(rep.getAll().size()))
                .findFirst()
                .get();
    }
}
