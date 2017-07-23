package example.com.weather;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

public class DialogFragment extends android.app.DialogFragment {

    FrameListener frameListener;

    public DialogFragment() {
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_dialog, null))
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        DialogFragment.super.onCancel(dialog);
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        frameListener.onDialogPositiveClick(DialogFragment.this);

                    }
                });

        return builder.create();
    }

    public void onStart() {
        super.onStart();
        Button button = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_NEGATIVE);
        Button button1 = ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(getResources().getColor(R.color.colorPrimary));
        button1.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        frameListener = (FrameListener) context;
    }

    public interface FrameListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

}
