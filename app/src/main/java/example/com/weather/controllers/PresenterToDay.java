package example.com.weather.controllers;

import android.content.Context;
import android.widget.Toast;


import example.com.weather.model.CallbackWeather;
import example.com.weather.model.oneday.ResponseOneDay;
import example.com.weather.views.MainActivity;

public class PresenterToDay implements CallbackWeather<ResponseOneDay> {
    private Context context;
    private ProviderWeather provider = new ProviderWeather();
    private float temp;
    private String name;
    private String icon;
    private String description;
    private Setplaces setplaces;

    public PresenterToDay(MainActivity activity) {
        setplaces =  activity;
        context = activity.getApplicationContext();

    }

    public void getTitle() {
        provider.getTitleWeather(this);
    }

    public void setView() {
        setplaces.setTemp(temp);
        setplaces.setName(name);
        setplaces.setIcon(icon);
        setplaces.setDescription(description);
    }

    @Override
    public void successWeather(ResponseOneDay weatherDate) {
        description = weatherDate.getWeather().get(0).getDescription();
        temp = weatherDate.getMain().getTemp();
        name = weatherDate.getName();
        icon = weatherDate.getWeather().get(0).getIcon();
    }

    @Override
    public void failWeather() {
        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
    }

}



