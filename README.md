# Tool for managing questions and prepare for exams
Note: study project

Features:
----
- For storing questions HashSet and HashMap were used
- Can receive questions and save them in repository
- User can request specific amount of questions and list will be generated containing questions randomly chosen from different subjects
- All math questions are randomly generated


Stack:
----
- Java 11
- Spring boot
- Junit

Endpoints:
----
- /exam/java/add (string params: question, answer);
- /exam/java/remove (string params: question, answer);
- exam/questions (int param: amount);
