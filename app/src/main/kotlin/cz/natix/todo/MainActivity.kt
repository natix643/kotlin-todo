package cz.natix.todo

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity(), TextInputDialog.Callback {

    private lateinit var adapter: TodoAdapter

    private lateinit var listView: ListView
    private lateinit var emptyListText: TextView
    private lateinit var floatingButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        listView = findViewById(android.R.id.list) as ListView
        emptyListText = findViewById(android.R.id.empty) as TextView

        floatingButton = findViewById(R.id.floatingButton) as FloatingActionButton
        floatingButton.setOnClickListener { TextInputDialog().show(fragmentManager, "") }

        adapter = TodoAdapter(this)

        listView.adapter = adapter
        listView.emptyView = emptyListText
        registerForContextMenu(listView)
    }

    override fun onTextInput(text: String) {
        adapter.add(Todo(text))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.menu_delete_completed -> {
                    val completedTodos = adapter.items.filter { it.isCompleted }
                    adapter.removeAll(completedTodos)
                    true
                }
                else -> false
            }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo) {
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem) =
            if (item.itemId == R.id.menu_delete) {
                val info = item.menuInfo as AdapterContextMenuInfo
                adapter.remove(info.position)
                true
            } else false

}
