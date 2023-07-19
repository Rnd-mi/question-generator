package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionIsAlreadyAdded;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.QuestionNotFound;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class JavaQuestionServiceTest {
    private final QuestionService out = new JavaQuestionService();

    @BeforeEach
    public void addQuestion() {
        out.add(Q1, A1);
        out.add(Q2, A2);
    }

    @Test
    void add_shouldAddCorrectlyIfQuestionIsUnique() {
        assertEquals(2, out.getAll().size());
        out.add(Q3, A3);
        assertEquals(3, out.getAll().size());
    }

    @Test
    void add_shouldThrowIfQuestionIsAlreadyIn() {
        assertThrows(QuestionIsAlreadyAdded.class, () -> out.add(Q1, A1));
        assertThrows(QuestionIsAlreadyAdded.class, () -> out.add(Q2, A2));
    }

    @Test
    void remove_shouldThrowIfQuestionNotFound() {
        assertThrows(QuestionNotFound.class,
                () -> out.remove(E3));
    }

    @Test
    void testRemove() {
        assertEquals(2, out.getAll().size());
        out.remove(E1);
        assertEquals(1, out.getAll().size());
    }

    @Test
    void testGetAll() {
        Set<Question> expected = new HashSet<>() {{
            add(E1);
            add(E2);
        }};
        assertIterableEquals(expected, out.getAll());
    }

    @Test
    void getRandomQuestion_shouldReturnPresentQuestion() {
        String actual = out.getRandomQuestion().getQuestion();
        String expected = Q1 + Q2;
        assertTrue(expected.contains(actual));
    }
}
