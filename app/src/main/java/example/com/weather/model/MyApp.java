package example.com.weather.model;

import android.app.Application;


public class MyApp extends Application {

    private WeatherModel weatherModel;

    @Override
    public void onCreate(){
        super.onCreate();
        weatherModel = new WeatherModel();
    }

    public WeatherModel create(){
        return  weatherModel;
    }
}
