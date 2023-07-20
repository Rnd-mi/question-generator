package ru.stepanov.skypro.coursework.examwebapp.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class MathQuestionRepositoryTest {
    private final QuestionRepository out = new MathQuestionRepository();

    @BeforeEach
    void init() {
        out.add(E1);
        out.add(E2);
    }

    @Test
    void add_shouldAddCorrectlyIfQuestionIsUnique() {
        assertEquals(2, out.getAll().size());
        out.add(E3);
        assertEquals(3, out.getAll().size());
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
}