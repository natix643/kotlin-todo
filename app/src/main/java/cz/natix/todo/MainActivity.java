package cz.natix.todo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.Collection;

public class MainActivity extends AppCompatActivity implements TextInputDialog.Callback {

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

        listView.setAdapter(adapter);
        listView.setEmptyView(emptyListText);
        registerForContextMenu(listView);
    }

    @Override
    public void onTextInput(String text) {
        adapter.add(new Todo(text));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete_completed) {
            Collection<Todo> completedTodos = Collections2.filter(adapter.getItems(), isCompleted);
            adapter.removeAll(completedTodos);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
            adapter.remove(info.position);
            return true;
        } else {
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
