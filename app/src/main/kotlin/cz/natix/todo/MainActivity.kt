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

    private val adapter by lazy { TodoAdapter(this) }

    private val listView by lazy { findViewById(android.R.id.list) as ListView }
    private val emptyListText by lazy { findViewById(android.R.id.empty) as TextView }
    private val floatingButton by lazy { findViewById(R.id.floatingButton) as FloatingActionButton }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        floatingButton.setOnClickListener { TextInputDialog().show(fragmentManager, "") }

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
