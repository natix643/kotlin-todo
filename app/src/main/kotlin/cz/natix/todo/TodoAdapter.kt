package cz.natix.todo

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import java.util.*

class TodoAdapter(context: Context) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    val items = ArrayList<Todo>()

    override fun getCount() = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int) = position.toLong()

    fun add(todo: Todo) {
        items.add(todo)
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeAll(todos: Collection<Todo>) {
        items.removeAll(todos)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val todo = getItem(position)

        val layout = inflater.inflate(R.layout.item, parent, false)

        val textView = layout.findViewById(R.id.todoText) as TextView
        textView.text = todo.text

        val checkBox = layout.findViewById(R.id.todoCompleted) as CheckBox
        checkBox.isChecked = todo.isCompleted
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            todo.isCompleted = isChecked

            val flags = textView.paintFlags
            if (isChecked) {
                textView.paintFlags = flags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                textView.paintFlags = flags xor Paint.STRIKE_THRU_TEXT_FLAG
            }
        }

        return layout
    }

}
