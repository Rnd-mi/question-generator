package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.MethodNotAllowedException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class MathQuestionServiceTest {
    private QuestionService out;

    @BeforeEach
    void init() {
        out = new MathQuestionService();
    }

    @Test
    void add_shouldThrowIfQuestionIsAlreadyIn() {
        assertThrows(MethodNotAllowedException.class, () -> out.add(Q1, A1));
    }

    @Test
    void remove_shouldThrowIfQuestionNotFound() {
        assertThrows(MethodNotAllowedException.class,
                () -> out.remove(E4));
    }

    @Test
    void getRandomQuestion_shouldReturnPresentQuestion() {
        String actual = out.getRandomQuestion().getQuestion();
        String expected = "?";
        assertTrue(actual.contains(expected));
    }
}
