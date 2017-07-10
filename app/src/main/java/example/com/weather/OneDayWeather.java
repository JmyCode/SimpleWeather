package example.com.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OneDayWeather extends AppCompatActivity {

    public static final String EXTRA_WEATHER= "weather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_weather);
    }
}
