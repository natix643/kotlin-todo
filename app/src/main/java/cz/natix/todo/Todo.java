package cz.natix.todo;

public class Todo {

    private final String text;
    private boolean completed;

    public Todo(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
