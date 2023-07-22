package ru.stepanov.skypro.coursework.examwebapp.services.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionNotFoundException;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionIsAlreadyAddedException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.repositories.QuestionRepository;
import ru.stepanov.skypro.coursework.examwebapp.services.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository rep;
    private final Random random;

    @Autowired
    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository rep) {
        this.rep = rep;
        random = new Random();
    }

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository rep,
                               Random random) {
        this.rep = rep;
        this.random = random;
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
        List<Question> list = new ArrayList<>(rep.getAll());
        return list.get(random.nextInt(rep.getAll().size()));
    }
}
