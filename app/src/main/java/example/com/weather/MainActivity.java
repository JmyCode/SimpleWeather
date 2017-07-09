package example.com.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private final String KEY = "21a8d636ae57d56ec6fb2ebb46d3e0b4";
    private final double LAT = 52.73;
    private final double LON = 41.43;
    private ResponseObj resultWeather;
    TextView textWeather;
    TextView weather;
    ImageView imageView;
    TextView textTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textTemp = (TextView) findViewById(R.id.text_temp);
         RequestWeather requestWeather = ServiceGenerator.create(RequestWeather.class);

        requestWeather.getWeather(LAT, LON, "metric", KEY).enqueue(new Callback<ResponseObj>() {
            @Override
            public void onResponse(Call<ResponseObj> call, Response<ResponseObj> response) {
                resultWeather = response.body();
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_items);
                AdapterWeather adapter = new AdapterWeather(textWeather, weather, imageView, resultWeather);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                textTemp.setText(response.body().getCity().getName());

            }
            @Override
            public void onFailure(Call<ResponseObj> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error ", Toast.LENGTH_LONG).show();
            }
        });



    }
}


































