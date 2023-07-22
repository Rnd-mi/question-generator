package ru.stepanov.skypro.coursework.examwebapp.repositories;

import org.springframework.stereotype.Repository;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> rep = new HashSet<>();

    public JavaQuestionRepository() {

    }

    @PostConstruct
    public void init() {
        add(new Question("say, what access modifiers there are?",
                "public, default, protected, private"));
        add(new Question("decode the JVM abbreviation", "Java Virtual Machine"));
        add(new Question("describe the polymorphism principle in java",
                "for example, invoking the particular method on different objects can lead to different actions"));
        add(new Question("where the objects are stored", "in the heap memory"));
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
