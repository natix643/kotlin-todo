package cz.natix.todo;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<Todo> todos = new ArrayList<>();

    {
        todos.add(new Todo("Make a presentation :/"));
        todos.add(new Todo("Do not panic", true));
    }

    public List<Todo> getTodos() {
        return todos;
    }

}
