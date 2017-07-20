package example.com.weather.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import example.com.weather.R;


public class AdapterByHour extends RecyclerView.Adapter<AdapterByHour.ViewHolder> {
    private ArrayList<String> dateArray;
    private ArrayList<String> icon;
    private Context context;
    private ArrayList<Float> tempValue;

    public AdapterByHour(ArrayList<Float> tempValue, ArrayList<String> dateArray, ArrayList<String> icon, Context context) {
        this.context = context;
        this.tempValue = tempValue;
        this.dateArray = dateArray;
        this.icon = icon;
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
        Picasso.with(context).load("http://openweathermap.org/img/w/" + icon.get(position) + ".png")
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
}
