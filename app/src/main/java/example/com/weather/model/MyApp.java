package example.com.weather.model;

import android.app.Application;

import example.com.weather.model.oneday.Weather;

/**
 * Created by Pavel on 31.07.2017.
 */

public class MyApp extends Application {

    private static WeatherModel model;


    public void onCreate(){
        super.onCreate();
        model = new WeatherModel();
    }


    public static WeatherModel getInstance(){
        return model;
    }


}
