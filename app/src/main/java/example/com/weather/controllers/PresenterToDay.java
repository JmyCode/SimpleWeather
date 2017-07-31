package example.com.weather.controllers;

import android.content.Context;
import android.widget.Toast;

import example.com.weather.model.CallbackWeather;
import example.com.weather.model.oneday.ResponseOneDay;
import example.com.weather.views.MainActivity;

public class PresenterToDay implements CallbackWeather<ResponseOneDay> {
    private Context context;
    private ProviderWeather provider = new ProviderWeather();
    private ResponseOneDay responseOneDay = new ResponseOneDay();

    Setplaces setplaces;

    public PresenterToDay(MainActivity activity) {
        setplaces =  activity;
        context = activity.getApplicationContext();

    }

    public void getTitle() {
        provider.getTitleWeather(this);
    }

    public void setView() {
        setplaces.setTemp(responseOneDay.getMain().getTemp());
        setplaces.setName(responseOneDay.getName());
        setplaces.setIcon(responseOneDay.getWeather().get(0).getIcon());
    }

    @Override
    public void successWeather(ResponseOneDay weatherDate) {
        responseOneDay = weatherDate;
    }

    @Override
    public void failWeather() {
        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
    }

}



