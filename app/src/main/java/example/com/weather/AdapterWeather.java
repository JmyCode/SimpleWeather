package example.com.weather;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import utility.DateFormatter;


public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.ViewHolder> {
    // private List<example.com.weather.response.List> date;
    List<example.com.weather.forecast.List> date;
    private Context context;
    private DateFormatter dateFormatter = new DateFormatter();
    private Listener listener;

    AdapterWeather(Context context, List<example.com.weather.forecast.List> date) {
        this.date = date;
        this.context = context;
    }

    public void setListener(Listener listener) {
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

        weather.setText(context.getString(R.string.temp_value, date.get(position).getTemp().getDay()));


        dateFormatter.formatDate(position, weatherTitle);
        dateFormatter.pushDate(position, date, context, imageView, rainText);

        winterSpeed.setText(context.getString(R.string.winter, date.get(position).getSpeed()));


        pressure.setText(context.getString(R.string.pressure, date.get(position).getPressure()));



//Date d = new Date();
        /*Для ResponseObj*/
//        weatherTitle.setText(date.get(position).getDt_txt());
//        weather.setText(String.valueOf(date.get(position).getMain().getTemp()+ "°C"));
//        for (Weather w : date.get(position).getWeather()) {
//            pathIcon = w.getIcon();
//            Picasso.with(context).load("http://openweathermap.org/img/w/" + pathIcon + ".png")
//                    .into(imageView);
//        }

        cv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

    }

    public int getItemCount() {
        return 5;
    }

    interface Listener {
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
