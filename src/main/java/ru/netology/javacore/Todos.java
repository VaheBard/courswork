package ru.netology.javacore;

import java.util.*;

public class Todos {
    private static final int MAX_AMOUNT_OF_TASKS = 7;
    private final Deque<List<String>> stack = new LinkedList<>();
    private List<String> actual = new LinkedList<>();

    public void addTask(String task) {
        if (actual.size() < MAX_AMOUNT_OF_TASKS) {
            // Перед выполнением действия сохраняем текущее состояние в стэк
            stack.add(new LinkedList<>(actual));
            actual.add(task);
        }
    }

    public void removeTask(String task) {
        if (actual.contains(task)) {
            // Перед выполнением действия сохраняем текущее состояние в стэк
            stack.add(new LinkedList<>(actual));
            actual.remove(task);
        }
    }

    public String getAllTasks() {
        Collections.sort(actual); // сортировка в лексикографическом порядке
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : actual) {
            if ((stringBuilder.toString().equals(""))) {
                stringBuilder.append(item);
            } else {
                stringBuilder.append(" ").append(item);
            }
        }
        return stringBuilder.toString();
    }

    public void restoreTask() {
        actual = stack.pollLast();
    }
}