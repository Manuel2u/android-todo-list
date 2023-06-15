package com.example.todoapp;

public class TodoItem {
    private String description;
    private boolean completed;

    public TodoItem(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
