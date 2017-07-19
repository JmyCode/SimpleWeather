package example.com.weather;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdapterByHour extends RecyclerView.Adapter<AdapterByHour.ViewHolder> {
    ArrayList<String> dateArray;
    ArrayList<String> pic;
    Context context;
    ArrayList<Float> f;

    AdapterByHour(ArrayList<Float> f, ArrayList<String> dateArray, ArrayList<String> pic, Context context) {
        this.context = context;
        this.f = f;
        this.dateArray = dateArray;
        this.pic = pic;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int item) {
        CardView card = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_by_hour, parent, false);
        return new ViewHolder(card);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView card = holder.c;
        TextView textView = (TextView) card.findViewById(R.id.time_weather);
        TextView textView1 = (TextView) card.findViewById(R.id.day_weather);
        ImageView img = (ImageView) card.findViewById(R.id.img);
        textView1.setText(f.get(position) + "");
        textView.setText(dateArray.get(position) + "");
        Picasso.with(context).load("http://openweathermap.org/img/w/" + pic.get(position) + ".png")
                .into(img);
    }

    public int getItemCount() {
        return f.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView c;

        ViewHolder(CardView c) {
            super(c);
            this.c = c;
        }
    }
}
