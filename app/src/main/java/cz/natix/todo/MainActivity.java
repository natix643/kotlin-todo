package cz.natix.todo;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TodoAdapter adapter = new TodoAdapter(this);
        getListView().setAdapter(adapter);

        adapter.add(new Todo("Make a presentation :/"));
        adapter.add(new Todo("Do not panic", true));
    }

}
