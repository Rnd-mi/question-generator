package ru.stepanov.skypro.coursework.examwebapp.constants;

import ru.stepanov.skypro.coursework.examwebapp.model.Question;

public class Constants {
    public static final String Q1 = "What is DI?";
    public static final String A1 =
            "Dependency injection. " +
            "It's technique in which an object receives other object that it depends on";
    public static final String Q2 = "Where are the primitive objects stored?";
    public static final String A2 = "In the stack memory";
    public static final String Q3 = "empty";
    public static final String A3 = "empty";
    public static final Question E1 = new Question(Q1, A1);
    public static final Question E2 = new Question(Q2, A2);
    public static final Question E3 = new Question(Q3, A3);
}
