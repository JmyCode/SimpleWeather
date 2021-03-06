package example.com.weather.model;


import example.com.weather.model.forecast.ForecastObj;
import example.com.weather.model.forecast.List;
import example.com.weather.model.oneday.ResponseOneDay;
import example.com.weather.model.response.ResponseObj;
import example.com.weather.network.RequestWeather;
import example.com.weather.network.ServiceGenerator;
import example.com.weather.utility.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static example.com.weather.utility.Constants.DIMENSION;
import static example.com.weather.utility.Constants.KEY;
import static example.com.weather.utility.Constants.LANG;
import static example.com.weather.utility.Constants.days;


public class WeatherModel {

    private CityModel cityModel = new CityModel();
    private RequestWeather requestWeather = ServiceGenerator.create(RequestWeather.class);

    public CityModel getCityModel() {
        return cityModel;
    }

    public void getAllWeather(CallbackWeather callbackWeather) {
        requestWeather.getWeather(cityModel.getCityName(), days, LANG, DIMENSION, KEY).enqueue(new Callback<ForecastObj>() {
            @Override
            public void onResponse(Call<ForecastObj> call, Response<ForecastObj> response) {
                if (response.body() != null) {
                    ForecastObj jSonResponse = response.body();
                    java.util.List<List> date = jSonResponse.getList();
                    callbackWeather.successWeather(date);
                }
            }

            @Override
            public void onFailure(Call<ForecastObj> call, Throwable t) {
                callbackWeather.failWeather();
            }
        });
    }

    public void getWeatherByHours(CallbackWeather callbackWeather) {
        requestWeather.getWeatherByHour(cityModel.getCityName(), Constants.LANG, Constants.DIMENSION, Constants.KEY).enqueue(new Callback<ResponseObj>() {
            @Override
            public void onResponse(Call<ResponseObj> call, Response<ResponseObj> response) {
                if (response.body() != null) {
                    ResponseObj weatherByHour = response.body();
                    java.util.List<example.com.weather.model.response.List> dateByDay = weatherByHour.getList();
                    callbackWeather.successWeather(dateByDay);
                }
            }

            @Override
            public void onFailure(Call<ResponseObj> call, Throwable t) {
                callbackWeather.failWeather();
            }
        });
    }

    public void getCurrent(CallbackWeather callbackWeather) {
        requestWeather.getCurrentWeather(cityModel.getCityName(), Constants.LANG, Constants.DIMENSION, Constants.KEY).enqueue(new Callback<ResponseOneDay>() {
            @Override
            public void onResponse(Call<ResponseOneDay> call, Response<ResponseOneDay> response) {
                if (response.body() != null) {
                    ResponseOneDay weatherByDay = response.body();
                    callbackWeather.successWeather(weatherByDay);
                }
            }

            @Override
            public void onFailure(Call<ResponseOneDay> call, Throwable t) {
                callbackWeather.failWeather();
            }
        });
    }
}

