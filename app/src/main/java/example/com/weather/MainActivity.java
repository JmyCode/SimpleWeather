package example.com.weather;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;
import example.com.weather.response.List;
import example.com.weather.response.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String KEY = "21a8d636ae57d56ec6fb2ebb46d3e0b4";
    private final double LAT = 52.73;
    private final double LON = 41.43;
    private ResponseObj re;
    private TextView textTemp;
    private TextView todayWeather;
    private RecyclerView recyclerView;
    private AdapterWeather adapter;
    private java.util.List<List> dateWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        textTemp = (TextView) findViewById(R.id.text_temp);
        todayWeather = (TextView) findViewById(R.id.temp_today);
        final RequestWeather requestWeather = ServiceGenerator.create(RequestWeather.class);

        requestWeather.getWeather(LAT, LON, "metric", KEY).enqueue(new Callback<ResponseObj>() {
            @Override
            public void onResponse(Call<ResponseObj> call, Response<ResponseObj> response) {
                re = response.body();
                dateWeather = re.getList();
                adapter = new AdapterWeather(getApplicationContext(), dateWeather);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                textTemp.setText(response.body().getCity().getName());
                todayWeather.setText(String.valueOf(dateWeather.get(0).getMain().getTemp_max()+"°C"));

                adapter.setListener(new AdapterWeather.Listener() {
                    public void onClick(int position) {
                        Intent intent = new Intent(getApplicationContext(), OneDayWeather.class);
                        intent.putExtra(OneDayWeather.EXTRA_WEATHER, position);
                        intent.putExtra("date", dateWeather.get(position).getDt_txt());
                        intent.putExtra( "temp", dateWeather.get(position).getMain().getTemp());
                        for(Weather w : dateWeather.get(position).getWeather())
                        intent.putExtra("icon", w.getIcon());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<ResponseObj> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error ", Toast.LENGTH_LONG).show();
            }
        });
    }


}


































