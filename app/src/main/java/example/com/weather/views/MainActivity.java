package example.com.weather.views;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import example.com.weather.R;
import example.com.weather.controllers.Setplaces;
import example.com.weather.controllers.AdapterWeather;
import example.com.weather.controllers.PresenterToDay;
import example.com.weather.utility.DateFormatter;


public class MainActivity extends AppCompatActivity implements Setplaces {
    private AlertDialog dialFindCity;

    private AdapterWeather adapterWeatherByDaysWeek;
    private PresenterToDay presenterToDay;
    private CollapsingToolbarLayout ctl;
    private TextView tempTitle;
    private TextView descriptionTitle;
    private ImageView iconTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindCityDialog dialog = new FindCityDialog(this);
        dialFindCity = dialog.getDialog(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.i_toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapterWeatherByDaysWeek = new AdapterWeather(this);
        recyclerView.setAdapter(adapterWeatherByDaysWeek);
        presenterToDay = new PresenterToDay(this);
        iconTitle = (ImageView) findViewById(R.id.icon_title);
        descriptionTitle = (TextView) findViewById(R.id.rain_in_title);
        tempTitle = (TextView) findViewById(R.id.temp_today);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        ctl.setExpandedTitleColor(Color.BLACK);
        adapterWeatherByDaysWeek.setItemClickListener((position) -> {
            DateFormatter dateFormatter = new DateFormatter("dd.MM.yyyy");
            String resultDate = dateFormatter.transformDate(position);
            Intent intent = new Intent(getApplicationContext(), OneDayWeatherActivity.class);
            intent.putExtra(OneDayWeatherActivity.EXTRA_WEATHER, resultDate);
            startActivity(intent);
        });
    }

    protected void onStart() {
        super.onStart();
        adapterWeatherByDaysWeek.updateWeather();
        presenterToDay.updateTitle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.find:
                dialFindCity.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTemp(float tempToday) {
        tempTitle.setText(this.getString(R.string.temp_value, tempToday));
    }

    @Override
    public void setName(String title) {
        ctl.setTitle(title);
    }

    @Override
    public void setIcon(String path) {
        Picasso.with(this).load(presenterToDay.getIconPath()
                +path
                +presenterToDay.getIconType())
                .into(iconTitle);
    }

    @Override
    public void setDescription(String description) {
        this.descriptionTitle.setText(description);
    }
}





































