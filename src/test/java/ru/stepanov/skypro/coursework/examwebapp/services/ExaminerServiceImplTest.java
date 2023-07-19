package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.InvalidAmountException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService mock;

    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void testGetQuestions() {
        when(mock.getAll()).thenReturn(generateSet());
        when(mock.getRandomQuestion()).thenReturn(E1);

        assertEquals(E1,
                out.getQuestions(1).stream().findFirst().get());
    }

    @Test
    void getQuestion_shouldThrowIfInvalidAmount() {
        assertThrows(InvalidAmountException.class, () -> out.getQuestions(-10));
        assertThrows(InvalidAmountException.class, () -> out.getQuestions(3));
        verify(mock, times(2)).getAll();
        verify(mock, never()).getRandomQuestion();

        when(mock.getAll()).thenReturn(generateSet());
        when(mock.getRandomQuestion())
                .thenReturn(E1)
                .thenReturn(E2)
                .thenReturn(E3);

        assertDoesNotThrow(() -> out.getQuestions(3));

        verify(mock, times(3)).getRandomQuestion();
    }

    private Set<Question> generateSet() {
        return new HashSet<>() {{
            add(E1);
            add(E2);
            add(E3);
        }};
    }
}