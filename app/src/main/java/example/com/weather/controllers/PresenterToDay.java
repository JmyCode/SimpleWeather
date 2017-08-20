package example.com.weather.controllers;

import android.content.Context;
import android.widget.Toast;

import example.com.weather.model.CallbackWeather;
import example.com.weather.MyApp;
import example.com.weather.model.WeatherModel;
import example.com.weather.model.oneday.ResponseOneDay;

public class PresenterToDay implements CallbackWeather<ResponseOneDay> {

    private Context context;
    private Setplaces setplaces;
    private WeatherModel weatherModel;

    public PresenterToDay(Setplaces setplaces) {
        this.setplaces = setplaces;
        context = (Context) setplaces;
        weatherModel = MyApp.getInstance(context).getModel();
    }

    public void updateTitle() {
        weatherModel.getCurrent(this);
    }

    @Override
    public void successWeather(ResponseOneDay weatherDate) {
        float temp = weatherDate.getMain().getTemp();
        String name = weatherDate.getName();
        String icon = weatherDate.getWeather().get(0).getIcon();
        String description = weatherDate.getWeather().get(0).getDescription();
        setplaces.setTemp(temp);
        setplaces.setName(name);
        setplaces.setIcon(icon);
        setplaces.setDescription(description);
    }

    @Override
    public void failWeather() {
        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
    }

}



