package ru.stepanov.skypro.coursework.examwebapp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stepanov.skypro.coursework.examwebapp.exceptions.MethodNotAllowedException;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.services.math.MathOperation;
import ru.stepanov.skypro.coursework.examwebapp.services.math.MathQuestionService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static ru.stepanov.skypro.coursework.examwebapp.constants.Constants.*;

class MathQuestionServiceTest {
    private QuestionService out;
    private Random random;
    private List<MathOperation> operations;

    @BeforeEach
    void init() {
        random = mock(Random.class);
        operations = getOperations();
        out = new MathQuestionService(random, operations);
    }

    @Test
    void add_shouldThrow() {
        assertThrows(MethodNotAllowedException.class, () -> out.add(Q1, A1));
        assertThrows(MethodNotAllowedException.class, () -> out.add(new Question(Q3, A3)));
    }

    @Test
    void remove_shouldThrow() {
        assertThrows(MethodNotAllowedException.class,
                () -> out.remove(E4));
    }

    @Test
    void getAll_shouldThrow() {
        assertThrows(MethodNotAllowedException.class,
                () -> out.getAll());
    }

    @ParameterizedTest
    @MethodSource("provideArgs")
    void getRandomQuestion_shouldReturnPresentQuestion(int num1, int num2, int operation,
                                                       String expectedQ, String expectedA) {
        when(random.nextInt(eq(OPERATION))).thenReturn(operation);
        when(random.nextInt(eq(BOUND_0_1))).thenReturn(num1).thenReturn(num2);
        when(random.nextInt(eq(BOUND_2_3))).thenReturn(num1).thenReturn(num2);
        when(random.nextInt(eq(BOUND_2_3))).thenReturn(num1).thenReturn(num2).thenReturn(num1);
        when(random.nextInt(eq(BOUND_4))).thenReturn(num2 - 1);

        Question actual = out.getRandomQuestion();
        String actualQ = actual.getQuestion();
        String actualA = actual.getAnswer();

        assertEquals(expectedQ, actualQ);
        assertEquals(expectedA, actualA);

        verify(random, times(1)).nextInt(OPERATION);
    }

    private static Stream<Arguments> provideArgs() {
        return Stream.of(
                Arguments.of(20, 10, 0, "20 + 10 = ?", "30"),
                Arguments.of(20, 10, 1, "20 - 10 = ?", "10"),
                Arguments.of(20, 10, 2, "20 * 10 = ?", "200"),
                Arguments.of(20, 10, 3, "20 / 10 = ?", "2")
        );
    }

    private List<MathOperation> getOperations() {
        return List.of(
                () -> {
                    int num1 = random.nextInt(BOUND_0_1);
                    int num2 = random.nextInt(BOUND_0_1);
                    return new Question(num1 + " + " + num2 + " = ?",
                            String.valueOf(num1 + num2));
                },
                () -> {
                    int num1 = random.nextInt(BOUND_0_1);
                    int num2 = random.nextInt(BOUND_0_1);
                    return new Question(num1 + " - " + num2 + " = ?",
                            Integer.toString(num1 - num2));
                },
                () -> {
                    int num1 = random.nextInt(BOUND_2_3);
                    int num2 = random.nextInt(BOUND_2_3);
                    return new Question(num1 + " * " + num2 + " = ?",
                            Integer.toString(num1 * num2));
                },
                () -> {
                    int num1 = random.nextInt(BOUND_2_3);
                    int num2 = random.nextInt(BOUND_4) + 1;
                    double result = num1 / (double) num2;
                    DecimalFormat df = new DecimalFormat("#.##");
                    return new Question(num1 + " / " + num2 + " = ?", df.format(result));
                }
        );
    }
}
