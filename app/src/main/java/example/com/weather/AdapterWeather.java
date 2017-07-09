package example.com.weather;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class AdapterWeather extends RecyclerView.Adapter<AdapterWeather.ViewHolder> {

    private TextView weatherTitle;
    private TextView weather;
    private ImageView imageView;
    private ResponseObj weatherObj;

    AdapterWeather(TextView weatherTitle, TextView weather, ImageView imageWeather, ResponseObj weatherObj) {
        this.weatherTitle = weatherTitle;
        this.weather = weather;
        this.imageView = imageWeather;
        this.weatherObj = weatherObj;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int intItem) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_result, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cv = holder.cardView;
        weatherTitle = (TextView) cv.findViewById(R.id.weather_title);
        weather = (TextView) cv.findViewById(R.id.weather);
        weatherTitle.setText(weatherObj.getList().get(position).getDt_txt());
        example.com.weather.response.List l = weatherObj.getList().get(position);
        weather.setText(String.valueOf(l.getMain().getTemp_max() + "°C"));
//        String str = weatherObj.getList().get(0).getWeather().get(0).getIcon();
//        Picasso.with().load("http://openweathermap.org/img/w/" + str + ".png").into(imageView);
    }

    public int getItemCount() {
       return 5;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

    }


}
