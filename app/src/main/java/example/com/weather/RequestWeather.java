package example.com.weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pavel on 05.07.2017.
 */

public interface RequestWeather {
    @GET("data/2.5/forecast")
    Call<ResponseObj> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("units") String units, @Query("APPID") String key);


    @GET("http://api.openweathermap.org/data/2.5/forecast?lat=52.73&lon=41.43&units=metric&APPID=21a8d636ae57d56ec6fb2ebb46d3e0b4")
    Call<ResponseObj> getPathWeather();






}
