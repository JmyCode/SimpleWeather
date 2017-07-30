package example.com.weather.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import example.com.weather.utility.Constants;
import example.com.weather.R;
import example.com.weather.controllers.AdapterByHour;
import example.com.weather.model.response.List;
import example.com.weather.model.response.ResponseObj;
import example.com.weather.model.response.Weather;
import example.com.weather.model.rest.RequestWeather;
import example.com.weather.model.rest.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneDayWeather extends Activity {
    public static final String EXTRA_WEATHER = "weather";
    private RecyclerView recyclerView;
    private AdapterByHour adapterByHour;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_weather);
        intent = getIntent();
        String cityName = intent.getStringExtra("city");
        recyclerView = (RecyclerView) findViewById(R.id.rv_by_hour);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapterByHour = new AdapterByHour(getApplicationContext(), intent);

        recyclerView.setAdapter(adapterByHour);

    }

    protected void onStart(){
        super.onStart();
        adapterByHour.getweatherByDay();
    }
}
