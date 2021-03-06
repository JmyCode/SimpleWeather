package example.com.weather.controllers;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.weather.R;
import example.com.weather.model.CallbackWeather;
import example.com.weather.MyApp;
import example.com.weather.utility.DateFormatter;


public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.ViewHolder> implements CallbackWeather<List<example.com.weather.model.forecast.List>> {

    private List<example.com.weather.model.forecast.List> data = new ArrayList<>();
    private Context context;
    private DateFormatter dateFormatter = new DateFormatter("E dd.MM.yyyy");
    private Listener listener;

    public AdapterWeather(Context context) {
        this.context = context;
    }

    public void setItemClickListener(Listener listener) {
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int intItem) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_result, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cv = holder.cardView;
        ImageView imageView = (ImageView) cv.findViewById(R.id.image_weather);
        TextView weatherTitle = (TextView) cv.findViewById(R.id.weather_day_week);
        TextView weather = (TextView) cv.findViewById(R.id.weather);
        TextView rainText = (TextView) cv.findViewById(R.id.rain_text);
        TextView winterSpeed = (TextView) cv.findViewById(R.id.winter_speed);
        TextView pressure = (TextView) cv.findViewById(R.id.pressure);
        weather.setText(context.getString(R.string.temp_value, data.get(position).getTemp().getDay()));
        dateFormatter.formatDate(position, weatherTitle);
        dateFormatter.pushDate(position, data, context, imageView, rainText);
        winterSpeed.setText(context.getString(R.string.winter, data.get(position).getSpeed()));
        pressure.setText(context.getString(R.string.pressure, data.get(position).getPressure()));
        cv.setOnClickListener((view) -> {
                    if (listener != null) {
                        listener.onClick(position);
                    }
                }
        );
    }

    public void updateWeather() {
        MyApp.getInstance(context).getModel().getAllWeather(this);
    }

    public int getItemCount() {
        return data.size();
    }

    @Override
    public void successWeather(List<example.com.weather.model.forecast.List> date) {
        if (this.data.isEmpty()) {
            this.data.addAll(date);
        }
        notifyDataSetChanged();
    }

    @Override
    public void failWeather() {
        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
    }

    public interface Listener {
        void onClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

}
