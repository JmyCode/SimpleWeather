package example.com.weather.controllers;


import example.com.weather.model.CallbackWeather;
import example.com.weather.model.WeatherModel;



public class ProviderWeather {

    private WeatherModel model = new WeatherModel();


    public ProviderWeather() {

    }

    public void getWeekWeather(CallbackWeather weather){
        model.getAllWeather(weather);
    }

    public void getByDay(CallbackWeather weather){
        model.getWeatherByHours(weather);
    }

    public void getTitleWeather(CallbackWeather weather){
        model.getCurrent(weather);
    }
}
