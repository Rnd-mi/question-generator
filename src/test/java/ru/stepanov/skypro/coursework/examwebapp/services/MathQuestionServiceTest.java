package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionIsAlreadyAdded;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionNotFoundException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.repositories.QuestionRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class MathQuestionServiceTest {
    private QuestionRepository rep;
    private QuestionService out;

    @BeforeEach
    void init() {
        rep = mock(QuestionRepository.class);
        out = new MathQuestionService(rep);
        when(rep.getAll()).thenReturn(generateSet());
    }

    @Test
    void add_shouldThrowIfQuestionIsAlreadyIn() {
        assertThrows(QuestionIsAlreadyAdded.class, () -> out.add(Q1, A1));
        assertThrows(QuestionIsAlreadyAdded.class, () -> out.add(Q1, A2));
        assertThrows(QuestionIsAlreadyAdded.class, () -> out.add(E1));
    }

    @Test
    void remove_shouldThrowIfQuestionNotFound() {
        assertThrows(QuestionNotFoundException.class,
                () -> out.remove(E4));
    }

    @Test
    void getRandomQuestion_shouldReturnPresentQuestion() {
        String actual = out.getRandomQuestion().getQuestion();
        String expected = Q1 + Q2 + Q3;
        assertTrue(expected.contains(actual));
    }

    private Set<Question> generateSet() {
        return new HashSet<>() {{
            add(E1);
            add(E2);
            add(E3);
        }};
    }
}