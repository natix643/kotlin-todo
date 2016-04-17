package cz.natix.todo;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<Todo> todos = new ArrayList<>();

    public List<Todo> getTodos() {
        return todos;
    }

}
