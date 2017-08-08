package example.com.weather.views;

import android.app.*;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import example.com.weather.R;
import example.com.weather.MyApp;
import example.com.weather.model.WeatherModel;


public class FindCityDialog {

    private WeatherModel weatherModel;
    private EditText editText;

    FindCityDialog(Context context) {
        weatherModel = MyApp.getInstance(context).getModel();
}

    public AlertDialog getDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        editText = (EditText) view.findViewById(R.id.edit_city_title);
        builder.setView(view)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String result = editText.getText().toString();
                        if (result.isEmpty() || result.startsWith(" ")) {
                            result = "Tambov";
                        }
                        weatherModel.getCityModel().setCityName(result);
                        activity.recreate();
                    }
                });
        return builder.create();
    }
}
