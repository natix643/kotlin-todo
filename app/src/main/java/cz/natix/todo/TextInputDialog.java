package cz.natix.todo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class TextInputDialog extends DialogFragment {

    public interface Callback {
        void onTextInput(String text);
    }

    private Callback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (Callback) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View layout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog, null);
        final EditText editText = (EditText) layout.findViewById(R.id.editText);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_title)
                .setView(layout)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onTextInput(editText.getText().toString());
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }
}
