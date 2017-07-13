package example.com.weather;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OneDayWeather extends AppCompatActivity {

    public static final String EXTRA_WEATHER= "weather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_day_weather);
        Intent intent = getIntent();
        String temp = intent.getStringExtra("date");
        float tempC = intent.getFloatExtra("temp", 0);
        String icon = intent.getStringExtra("icon");
        TextView tempView = (TextView) findViewById(R.id.text_temp);
        TextView tempDay= (TextView) findViewById(R.id.temp_day);
        ImageView imageDay = (ImageView) findViewById(R.id.image_day);

        Picasso.with(getApplicationContext()).load("http://openweathermap.org/img/w/" + icon + ".png").into(imageDay);

        tempView.setText(temp);
        tempDay.setText(String.valueOf(tempC+"°C"));


    }
}
