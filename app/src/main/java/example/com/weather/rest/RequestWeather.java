package example.com.weather.rest;

import example.com.weather.forecast.ForecastObj;
import example.com.weather.response.ResponseObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RequestWeather {

    @GET("data/2.5/forecast/daily")
    Call<ForecastObj> getWeather(@Query("q") String q,
                                     @Query("lang") String lang,
                                     @Query("units") String units,
                                     @Query("APPID") String key);


    @GET("data/2.5/forecast")
    Call<ResponseObj> getWeatherByHour(@Query("q") String q,
                                          @Query("lang") String lang,
                                          @Query("units") String units,
                                          @Query("APPID") String key);

}
