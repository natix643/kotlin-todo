package cz.natix.todo;

public class Todo {

    private String text;
    private boolean completed;

    public Todo(String text) {
        this.text = text;
    }

    public Todo(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
