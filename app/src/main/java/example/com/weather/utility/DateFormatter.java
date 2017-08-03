package example.com.weather.utility;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import example.com.weather.R;
import example.com.weather.model.forecast.ForecastObj;
import example.com.weather.model.forecast.List;
import example.com.weather.model.forecast.Weather;

public class DateFormatter {

    private String pattern;

    public DateFormatter(String pattern) {
        this.pattern = pattern;
    }

    public String transformDate(int position) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +position);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        Date dateForecast = cal.getTime();
        return simpleDateFormat.format(dateForecast);
    }

    public void formatDate(int position, TextView weatherTitle) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, position);
        Date dateForecast = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String resultDate = simpleDateFormat.format(dateForecast);
        weatherTitle.setText(resultDate);
    }

    public void pushDate(int position, java.util.List<List> date, Context context, ImageView imageView, TextView rainText) {
        float rainForecast = (date.get(position).getRain());
        for (Weather w : date.get(position).getWeather()) {
            String pathIcon = w.getIcon();
            String descriptionClouds = w.getDescription();
            Picasso.with(context).load(Constants.iconPath + pathIcon + ".png")
                    .into(imageView);
            rainText.setText(context.getString(R.string.rain, descriptionClouds, rainForecast));
        }
    }
}
