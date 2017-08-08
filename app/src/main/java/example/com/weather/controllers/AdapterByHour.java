package example.com.weather.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import example.com.weather.model.CallbackWeather;
import example.com.weather.MyApp;
import example.com.weather.model.WeatherModel;
import example.com.weather.model.response.Weather;
import example.com.weather.utility.Constants;
import example.com.weather.R;
import example.com.weather.views.OneDayWeatherActivity;


public class AdapterByHour extends RecyclerView.Adapter<AdapterByHour.ViewHolder> implements CallbackWeather<List<example.com.weather.model.response.List>>{

    private List<example.com.weather.model.response.List> weatherByDay = new ArrayList<>();
    private List<String> dateArray = new ArrayList<>();
    private List<String> icon = new ArrayList<>();
    private Context context;
    private List<Float> tempValue = new ArrayList<>();
    private Intent intent;
    private WeatherModel weatherModel;

    public AdapterByHour(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        MyApp myApp= (MyApp)context.getApplicationContext();
        weatherModel = myApp.getModel();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int item) {
        CardView card = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_by_hour, parent, false);
        return new ViewHolder(card);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView card = holder.cardView;
        TextView dateValue = (TextView) card.findViewById(R.id.time_weather);
        TextView weatherValue = (TextView) card.findViewById(R.id.day_weather);
        ImageView img = (ImageView) card.findViewById(R.id.img);
        weatherValue.setText(context.getString(R.string.temp_value, tempValue.get(position)));
        dateValue.setText(context.getString(R.string.date_value, dateArray.get(position)));
        Picasso.with(context)
                .load(Constants.iconPath + icon.get(position) + ".png")
                .into(img);
    }

    public int getItemCount() {
        return tempValue.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    public void getWeatherByDay(){
        weatherModel.getWeatherByHours(this);
    }

    @Override
    public void successWeather(List<example.com.weather.model.response.List> weatherDate) {
        if(weatherByDay.isEmpty()) {
            weatherByDay.addAll(weatherDate);
        }
        String dateValue = intent.getStringExtra(OneDayWeatherActivity.EXTRA_WEATHER);
        for (example.com.weather.model.response.List comparableDate : weatherByDay) {
            String dateList = comparableDate.getDt_txt();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            try {
                Date date = formatter.parse(dateList);
                String newDateFormat = newFormat.format(date);
                if (newDateFormat.equals(dateValue)) {
                    tempValue.add(comparableDate.getMain().getTemp());
                    dateArray.add(comparableDate.getDt_txt());
                    for (Weather w : comparableDate.getWeather()) {
                        icon.add(w.getIcon());
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void failWeather() {
        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
    }
}
