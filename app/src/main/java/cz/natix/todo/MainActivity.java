package cz.natix.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements TextInputDialog.Callback {

    private Database database = new Database();

    private TodoAdapter adapter;

    private ListView listView;
    private TextView emptyListText;
    private FloatingActionButton floatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(android.R.id.list);
        emptyListText = (TextView) findViewById(android.R.id.empty);

        floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
        floatingButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new TextInputDialog().show(getFragmentManager(), "");
            }
        });

        adapter = new TodoAdapter(this);
        adapter.setItems(database.getTodos());

        listView.setAdapter(adapter);
        listView.setEmptyView(emptyListText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onTextInput(String text) {
        Todo todo = new Todo(text);
        adapter.add(todo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.menu_filter_all:
                adapter.setItems(database.getTodos());
                return true;
            case R.id.menu_filter_active: {
                Collection<Todo> todos = Collections2.filter(
                        database.getTodos(), Predicates.not(isCompleted));
                adapter.setItems(new ArrayList<>(todos));
                return true;
            }
            case R.id.menu_filter_completed: {
                Collection<Todo> todos = Collections2.filter(database.getTodos(), isCompleted);
                adapter.setItems(new ArrayList<>(todos));
                return true;
            }
            default:
                return false;
        }
    }

    private static final Predicate<Todo> isCompleted = new Predicate<Todo>() {
        @Override
        public boolean apply(Todo todo) {
            return todo.isCompleted();
        }
    };

}
