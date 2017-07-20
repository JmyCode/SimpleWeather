package utility;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import example.com.weather.R;
import example.com.weather.forecast.List;
import example.com.weather.forecast.Weather;


public class DateFormatter {
    public void formatDate(int position, TextView weatherTitle) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, position);
        Date dateForecast = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy", Locale.getDefault());
        String resultDate = simpleDateFormat.format(dateForecast);
        weatherTitle.setText(resultDate);
    }

    public void pushDate(int position, java.util.List<List> date, Context context, ImageView imageView, TextView rainText) {
        float rainForecast = (date.get(position).getRain());
        for (Weather w : date.get(position).getWeather()) {
            String pathIcon = w.getIcon();
            String descriptionClouds = w.getDescription();
            Picasso.with(context).load("http://openweathermap.org/img/w/" + pathIcon + ".png")
                    .into(imageView);
            rainText.setText(context.getString(R.string.rain, descriptionClouds, rainForecast));
        }

    }


}
