package cz.natix.todo

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText

class TextInputDialog : DialogFragment() {

    interface Callback {
        fun onTextInput(text: String)
    }

    private lateinit var callback: Callback

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        callback = activity as Callback
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val layout = LayoutInflater.from(activity).inflate(R.layout.dialog, null)
        val editText = layout.findViewById(R.id.editText) as EditText

        return AlertDialog.Builder(activity)
                .setTitle(R.string.dialog_title)
                .setView(layout)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    callback.onTextInput(editText.text.toString())
                }
                .setNegativeButton(android.R.string.cancel, null)
                .create()
    }
}
