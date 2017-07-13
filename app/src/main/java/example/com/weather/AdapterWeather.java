package example.com.weather;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import example.com.weather.response.Weather;


public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.ViewHolder> {
    private Context context;
    private List<example.com.weather.response.List> date;
    interface Listener{
        void onClick(int position);
    }

    private Listener listener;

    public void setListener(Listener listener){
        this.listener = listener;
    }

    AdapterWeather(Context context, List<example.com.weather.response.List> date) {
        this.date = date;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int intItem) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_result, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cv = holder.cardView;

        String pathIcon;
        ImageView imageView = (ImageView) cv.findViewById(R.id.image_weather);
        TextView weatherTitle = (TextView) cv.findViewById(R.id.weather_title);
        TextView weather = (TextView) cv.findViewById(R.id.weather);
        weatherTitle.setText(date.get(position).getDt_txt());
        weather.setText(String.valueOf(date.get(position).getMain().getTemp()+ "°C"));
        for (Weather w : date.get(position).getWeather()) {
            pathIcon = w.getIcon();
            Picasso.with(context).load("http://openweathermap.org/img/w/" + pathIcon + ".png")
                    .into(imageView);
        }

        cv.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(listener != null){
                    listener.onClick(position);
                }
            }
        });

    }

    public int getItemCount() {
        return date.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

    }


}
