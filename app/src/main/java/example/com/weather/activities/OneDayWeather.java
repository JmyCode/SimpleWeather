package example.com.weather.activities;

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

import example.com.weather.Constants;
import example.com.weather.R;
import example.com.weather.adapters.AdapterByHour;
import example.com.weather.response.List;
import example.com.weather.response.ResponseObj;
import example.com.weather.response.Weather;
import example.com.weather.rest.RequestWeather;
import example.com.weather.rest.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneDayWeather extends Activity {
    public static final String EXTRA_WEATHER = "weather";
    private RecyclerView recyclerView;
    private AdapterByHour adapterByHour;
    private ResponseObj weatherByHour;
    private java.util.List<List> listForecast;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_weather);
        intent = getIntent();
        String cityName = intent.getStringExtra("city");
        recyclerView = (RecyclerView) findViewById(R.id.rv_by_hour);
        RequestWeather requestWeather = ServiceGenerator.create(RequestWeather.class);
        requestWeather.getWeatherByHour(cityName, Constants.LANG, Constants.DIMENSION, Constants.KEY).enqueue(new Callback<ResponseObj>() {
            @Override
            public void onResponse(Call<ResponseObj> call, Response<ResponseObj> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(OneDayWeather.this);
                recyclerView.setLayoutManager(layoutManager);
                weatherByHour = response.body();
                if (weatherByHour != null)
                    listForecast = weatherByHour.getList();
                String dateValue = intent.getStringExtra(OneDayWeather.EXTRA_WEATHER);
                Date date;
                String newDateFormat;
                ArrayList<Float> tempList = new ArrayList<>();
                ArrayList<String> dateArray = new ArrayList<>();
                ArrayList<String> pic = new ArrayList<>();
                for (List l : listForecast) {
                    String dateList = l.getDt_txt();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                    try {
                        date = formatter.parse(dateList);
                        newDateFormat = newFormat.format(date);
                        if (newDateFormat.equals(dateValue)) {
                            tempList.add(l.getMain().getTemp());
                            dateArray.add(l.getDt_txt());
                            for (Weather w : l.getWeather()) {
                                pic.add(w.getIcon());
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                adapterByHour = new AdapterByHour(tempList, dateArray, pic, getApplicationContext());
                recyclerView.setAdapter(adapterByHour);
            }

            @Override
            public void onFailure(Call<ResponseObj> call, Throwable t) {
                Toast.makeText(OneDayWeather.this, "error ", Toast.LENGTH_LONG).show();
            }
        });

    }
}
