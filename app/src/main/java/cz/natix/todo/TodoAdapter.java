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

    public void setItems(List<Todo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void add(Todo todo) {
        items.add(todo);
        notifyDataSetChanged();
    }

    public void remove(Todo todo) {
        items.remove(todo);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = inflater.inflate(R.layout.item, parent, false);
        final TextView textView = (TextView) layout.findViewById(R.id.todoText);

        CheckBox checkBox = (CheckBox) layout.findViewById(R.id.todoCompleted);
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int flags = textView.getPaintFlags();

                if (isChecked) {
                    textView.setPaintFlags(flags | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    textView.setPaintFlags(flags ^ Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        Todo todo = getItem(position);
        textView.setText(todo.getText());
        checkBox.setChecked(todo.isCompleted());

        return layout;
    }

}
