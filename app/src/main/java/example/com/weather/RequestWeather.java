package example.com.weather;

import example.com.weather.forecast.ForecastObj;
import example.com.weather.response.ResponseObj;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RequestWeather {

    @GET("http://api.openweathermap.org/data/2.5/forecast?lat=52.73&lon=41.43&units=metric&APPID=21a8d636ae57d56ec6fb2ebb46d3e0b4")
    Call<ResponseObj> getPathWeather();

    @GET("data/2.5/forecast/daily")
    Call<ForecastObj> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("lang") String lang, @Query("units") String units, @Query("cnt") String cnt, @Query("APPID") String key);

}
