package example.com.weather;

import android.app.Application;
import android.content.Context;

import example.com.weather.model.WeatherModel;


public class MyApp extends Application {

    private WeatherModel weatherModel;

    @Override
    public void onCreate(){
        super.onCreate();
        weatherModel = new WeatherModel();
    }

    public WeatherModel getModel(){
        return  weatherModel;
    }

    public static MyApp getInstance(Context context){
        return (MyApp)context.getApplicationContext();
    }
}
