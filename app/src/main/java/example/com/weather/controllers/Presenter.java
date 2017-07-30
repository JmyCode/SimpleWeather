package example.com.weather.controllers;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import example.com.weather.ProviderWeather;
import example.com.weather.R;
import example.com.weather.model.CallbackWeather;
import example.com.weather.model.oneday.ResponseOneDay;

public class Presenter implements CallbackWeather<ResponseOneDay> {
    private Context context;
    private ProviderWeather provider = new ProviderWeather();
    private TextView temp;
    private ResponseOneDay responseOneDay = new ResponseOneDay();
    private CollapsingToolbarLayout ctl;



    public Presenter(View view, Context context) {
        this.context = context;
        temp = (TextView) view.findViewById(R.id.temp_today);
        temp.setTextColor(Color.BLACK);
        ctl = (CollapsingToolbarLayout) view.findViewById(R.id.collapsingToolbar);
        ctl.setExpandedTitleColor(Color.BLACK);

    }

    public void getTitle() {
        provider.getTitleWeather(this);
    }

    public void setView() {
        ctl.setTitle(responseOneDay.getName());

    }

    @Override
    public void successWeather(ResponseOneDay weatherDate) {
        responseOneDay = weatherDate;
    }

    @Override
    public void failWeather() {
        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
    }

}



