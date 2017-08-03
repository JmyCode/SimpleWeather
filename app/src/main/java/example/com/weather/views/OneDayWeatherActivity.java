package example.com.weather.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import example.com.weather.R;
import example.com.weather.controllers.AdapterByHour;

public class OneDayWeatherActivity extends Activity {
    public static final String EXTRA_WEATHER = "weather";
    private RecyclerView recyclerView;
    private AdapterByHour adapterByHour;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_weather);
        intent = getIntent();
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
