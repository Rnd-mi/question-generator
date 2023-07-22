package ru.stepanov.skypro.coursework.examwebapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.stepanov.skypro.coursework.examwebapp.model.Question;
import ru.stepanov.skypro.coursework.examwebapp.services.math.MathOperation;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import static ru.stepanov.skypro.coursework.examwebapp.configuration.Constants.*;

@Configuration
public class JavaConfig {
    private final Random random = new Random();

    @Bean
    public List<MathOperation> operations() {
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
