package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.InvalidAmountException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.services.java.JavaQuestionService;
import ru.stepanov.skypro.coursework.examwebapp.services.math.MathQuestionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class ExaminerServiceImplTest {
    private JavaQuestionService javaMock;
    private MathQuestionService mathMock;
    private Random random;
    private ExaminerServiceImpl out;

    @BeforeEach
    void init() {
        javaMock = mock(JavaQuestionService.class);
        mathMock = mock(MathQuestionService.class);
        random = mock(Random.class);
        out = new ExaminerServiceImpl(mathMock, javaMock, random);
    }

    @Test
    void testGetQuestions() {
        int amount = 4;
        when(javaMock.getAll()).thenReturn(generateJavaSet());
        when(mathMock.getRandomQuestion()).thenReturn(E1).thenReturn(E2);
        when(javaMock.getRandomQuestion()).thenReturn(E3).thenReturn(E4);
        when(random.nextInt(anyInt())).thenReturn(2);

        List<Question> expected = new ArrayList<>() {{
            add(E1);
            add(E2);
            add(E3);
            add(E4);
        }};
        List<Question> actual = new ArrayList<>(out.getQuestions(amount));

        assertEquals(amount, actual.size());
        assertIterableEquals(expected, actual);
        verify(javaMock, times(2)).getRandomQuestion();
        verify(mathMock, times(2)).getRandomQuestion();
    }

    @Test
    void getQuestions_shouldThrowIfInvalidAmountAndViceVersa() {
        assertThrows(InvalidAmountException.class, () -> out.getQuestions(-10));
        assertThrows(InvalidAmountException.class, () -> out.getQuestions(0));
        verify(mathMock, never()).getAll();
        verify(javaMock, never()).getRandomQuestion();
    }

    private List<Question> generateJavaSet() {
        return new ArrayList<>() {{
            add(E1);
            add(E2);
        }};
    }
}
