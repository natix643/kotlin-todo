package cz.natix.todo;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private final LayoutInflater inflater;

    private List<Todo> items = new ArrayList<>();

    public TodoAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Todo getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Todo> getItems() {
        return items;
    }

    public void add(Todo todo) {
        items.add(todo);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    public void removeAll(Collection<Todo> todos) {
        items.removeAll(todos);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Todo todo = getItem(position);

        View layout = inflater.inflate(R.layout.item, parent, false);

        final TextView textView = (TextView) layout.findViewById(R.id.todoText);
        textView.setText(todo.getText());

        CheckBox checkBox = (CheckBox) layout.findViewById(R.id.todoCompleted);
        checkBox.setChecked(todo.isCompleted());
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                todo.setCompleted(isChecked);

                int flags = textView.getPaintFlags();
                if (isChecked) {
                    textView.setPaintFlags(flags | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    textView.setPaintFlags(flags ^ Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        return layout;
    }

}
