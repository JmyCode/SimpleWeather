package example.com.weather.views;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.com.weather.MyApp;
import example.com.weather.R;
import example.com.weather.model.WeatherModel;


public class DialogFragment extends android.app.DialogFragment {

    private WeatherModel weatherModel;
    private EditText editText;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        weatherModel = MyApp.getInstance(getActivity().getApplicationContext()).getModel();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        editText = (EditText) view.findViewById(R.id.edit_city_title);
        builder.setView(view)
                .setNegativeButton(R.string.cancel, (DialogInterface dialog, int id) -> DialogFragment.super.onCancel(dialog))
                .setPositiveButton(R.string.ok, (DialogInterface dialog, int id) -> {
                    String result = editText.getText().toString();
                    if (result.isEmpty() || result.startsWith(" ")) {
                        return;
                    }
                    weatherModel.getCityModel().setCityName(result);
                    getActivity().recreate();
                    dismiss();
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
}
