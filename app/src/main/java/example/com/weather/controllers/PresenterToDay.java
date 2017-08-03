package example.com.weather.controllers;

import android.content.Context;
import android.widget.Toast;


import example.com.weather.model.CallbackWeather;
import example.com.weather.model.MyApp;
import example.com.weather.model.WeatherModel;
import example.com.weather.model.oneday.ResponseOneDay;
import example.com.weather.views.MainActivity;

public class PresenterToDay implements CallbackWeather<ResponseOneDay> {

    private Context context;
    private Setplaces setplaces;
    private String iconPath = "http://openweathermap.org/img/w/";
    private String iconType = ".png";
    private WeatherModel weatherModel;

    public PresenterToDay(MainActivity activity) {
        setplaces = activity;
        context = activity.getApplicationContext();
        MyApp myApp = (MyApp) context.getApplicationContext();
        weatherModel = myApp.create();
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

    public String getIconPath() {
        return iconPath;
    }

    public String getIconType() {
        return iconType;
    }

}



