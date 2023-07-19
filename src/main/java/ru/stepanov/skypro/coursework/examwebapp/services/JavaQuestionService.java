package ru.stepanov.skypro.coursework.examwebapp.services;

import org.springframework.stereotype.Service;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionNotFound;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionIsAlreadyAdded;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> rep = new HashSet<>();
    private final Random random = new Random();

//    {
//        add("say, what access modifiers there are?", "public, default, protected, private");
//        add("decode the JVM abbreviation", "Java Virtual Machine");
//        add("describe the polymorphism principle in java",
//                "for example, invoking the particular method on different objects can lead to different actions");
//        add("where the objects are stored", "in the heap memory");
//    }

    @Override
    public Question add(String q, String a) {
        Question question = new Question(q, a);
        return add(question);
    }

    @Override
    public Question add(Question question) {
        if (!rep.add(question)) {
            throw new QuestionIsAlreadyAdded();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!rep.remove(question)) {
            throw new QuestionNotFound();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return rep;
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

        return rep.stream()
                .skip(random.nextInt(rep.size()))
                .findFirst()
                .get();
    }
}
