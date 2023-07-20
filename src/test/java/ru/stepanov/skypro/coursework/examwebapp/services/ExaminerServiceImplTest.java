package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.InvalidAmountException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class ExaminerServiceImplTest {
    private JavaQuestionService javaMock;
    private MathQuestionService mathMock;
    private ExaminerServiceImpl out;

    @BeforeEach
    void init() {
        javaMock = mock(JavaQuestionService.class);
        mathMock = mock(MathQuestionService.class);
        out = new ExaminerServiceImpl(mathMock, javaMock);
    }

    @Test
    void testGetQuestions() {
        int amount = 2;
        when(javaMock.getAll()).thenReturn(generateJavaSet());
        when(mathMock.getAll()).thenReturn(generateMathSet());
        when(mathMock.getRandomQuestion()).thenReturn(E1).thenReturn(E2);
        when(javaMock.getRandomQuestion()).thenReturn(E3).thenReturn(E4);

        Set<Question> expected = new HashSet<>() {{
            add(E1);
            add(E2);
            add(E3);
            add(E4);
        }};
        Set<Question> actual = new HashSet<>(out.getQuestions(amount));

        assertTrue(expected.containsAll(actual));
        assertEquals(amount, actual.size());
    }

    @Test
    void getQuestion_shouldThrowIfInvalidAmountAndViceVersa() {
        assertThrows(InvalidAmountException.class, () -> out.getQuestions(-10));
        assertThrows(InvalidAmountException.class, () -> out.getQuestions(3));
        verify(mathMock, times(2)).getAll();
        verify(javaMock, never()).getRandomQuestion();

        when(javaMock.getAll()).thenReturn(generateJavaSet());
        when(mathMock.getAll()).thenReturn(generateMathSet());
        when(mathMock.getRandomQuestion()).thenReturn(E1).thenReturn(E2);
        when(javaMock.getRandomQuestion()).thenReturn(E3).thenReturn(E4);

        assertDoesNotThrow(() -> out.getQuestions(4));

        verify(javaMock, times(2)).getRandomQuestion();
        verify(mathMock, times(2)).getRandomQuestion();
    }

    private Set<Question> generateJavaSet() {
        return new HashSet<>() {{
            add(E1);
            add(E2);
        }};
    }

    private Collection<Question> generateMathSet() {
        return new HashSet<>() {{
            add(E3);
            add(E4);
        }};
    }
}
