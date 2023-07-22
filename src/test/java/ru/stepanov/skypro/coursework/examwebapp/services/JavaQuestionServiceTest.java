package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionIsAlreadyAddedException;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionNotFoundException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.repositories.QuestionRepository;
import ru.stepanov.skypro.coursework.examwebapp.services.java.JavaQuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class JavaQuestionServiceTest {
    private QuestionRepository rep;
    private QuestionService out;
    private Random random;

    @BeforeEach
    void init() {
        rep = mock(QuestionRepository.class);
        random = mock(Random.class);
        out = new JavaQuestionService(rep, random);
        when(rep.getAll()).thenReturn(generateList());
    }

    @Test
    void add_shouldThrowIfQuestionIsAlreadyIn() {
        assertThrows(QuestionIsAlreadyAddedException.class, () -> out.add(Q1, A1));
        assertThrows(QuestionIsAlreadyAddedException.class, () -> out.add(Q1, A2));
        assertThrows(QuestionIsAlreadyAddedException.class, () -> out.add(E1));
    }

    @Test
    void remove_shouldThrowIfQuestionNotFound() {
        assertThrows(QuestionNotFoundException.class,
                () -> out.remove(E4));
    }

    @Test
    void getRandomQuestion_shouldReturnPresentQuestion() {
        when(random.nextInt(anyInt()))
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(2);

        Question actual = out.getRandomQuestion();
        Question actual2 = out.getRandomQuestion();
        Question actual3 = out.getRandomQuestion();
        assertEquals(E1, actual);
        assertEquals(E2, actual2);
        assertEquals(E3, actual3);
        verify(random, times(3)).nextInt(anyInt());
    }

    @Test
    void testGetAll() {
        List<Question> expected = new ArrayList<>() {{
           add(E1);
           add(E2);
           add(E3);
        }};
        assertIterableEquals(expected, out.getAll());
        assertEquals(expected.size(), out.getAll().size());
    }

    private List<Question> generateList() {
        return new ArrayList<>() {{
            add(E1);
            add(E2);
            add(E3);
        }};
    }
}
