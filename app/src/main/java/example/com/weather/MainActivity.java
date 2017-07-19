package example.com.weather;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utility.DateFormatter;

public class MainActivity extends Activity {

    private final String KEY = "21a8d636ae57d56ec6fb2ebb46d3e0b4";
    private final double LAT = 52.73;
    private final double LON = 41.43;
    private ForecastObj re;
    private TextView todayWeather;
    private RecyclerView recyclerView;
    private AdapterWeather adapter;
    private TextView rainTitle;
    private ImageView titleIcon;
    private DateFormatter dateFormatter = new DateFormatter();
    List<example.com.weather.forecast.List> dateWeather;

    //private java.util.List<List> dateWeather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        todayWeather = (TextView) findViewById(R.id.temp_today);
        rainTitle = (TextView) findViewById(R.id.rain_in_title);
        titleIcon = (ImageView) findViewById(R.id.icon_title);
        RequestWeather requestWeather = ServiceGenerator.create(RequestWeather.class);


        requestWeather.getWeather(LAT, LON, "ru", "metric", "5", KEY).enqueue(new Callback<ForecastObj>() {
            @Override
            public void onResponse(Call<ForecastObj> call, Response<ForecastObj> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                re = response.body();
                dateWeather = re.getList();
                adapter = new AdapterWeather(getApplicationContext(), dateWeather);
                recyclerView.setAdapter(adapter);
                todayWeather.setText(getString(R.string.temp_value, dateWeather.get(0).getTemp().getDay()));
                dateFormatter.pushDate(0, dateWeather, getApplicationContext(), titleIcon, rainTitle);

                CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

                ctl.setTitle(response.body().getCity().getName());
                ctl.setExpandedTitleColor(Color.BLACK);


                    adapter.setListener(new AdapterWeather.Listener() {
                        public void onClick(int position) {
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE, +position);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                            Date dateForecast = cal.getTime();
                            String resultDate = simpleDateFormat.format(dateForecast);

                            Intent intent = new Intent(getApplicationContext(), OneDayWeather.class);
                            //intent.putExtra(OneDayWeather.EXTRA_WEATHER, position);
                            intent.putExtra("date", resultDate);
                            //intent.putExtra("position", position);
                            startActivity(intent);

                        }});
//                        intent.putExtra( "temp", dateWeather.get(position).getMain().getTemp());
//                        for(Weather w : dateWeather.get(position).getWeather())
//                        intent.putExtra("icon", w.getIcon());




        }

            @Override
            public void onFailure(Call<ForecastObj> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error ", Toast.LENGTH_LONG).show();
            }
        });}}





































