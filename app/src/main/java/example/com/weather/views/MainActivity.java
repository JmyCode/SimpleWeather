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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import example.com.weather.R;
import example.com.weather.controllers.Setplaces;
import example.com.weather.controllers.AdapterWeather;
import example.com.weather.controllers.PresenterToDay;
import example.com.weather.utility.Constants;


public class MainActivity extends AppCompatActivity implements Setplaces {

    private Toolbar toolbar;
    private AlertDialog dial;
    private FindCityDialog dialog = new FindCityDialog();
    private RecyclerView recyclerView;
    private AdapterWeather adapter;
    private PresenterToDay presenter;
    private CollapsingToolbarLayout ctl;
    private TextView temp;
    private TextView description;
    private ImageView icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dial = dialog.getDialog(this);
        toolbar = (Toolbar) findViewById(R.id.i_toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterWeather(this);
        recyclerView.setAdapter(adapter);
        presenter = new PresenterToDay(this);
        icon = (ImageView) findViewById(R.id.icon_title);
        description = (TextView) findViewById(R.id.rain_in_title);
        temp = (TextView) findViewById(R.id.temp_today);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        ctl.setExpandedTitleColor(Color.BLACK);
        config();


    }

    protected void onStart(){
        super.onStart();
        adapter.updateWeather();


        presenter.updateTitle();
    }

    public void config() {

            adapter.setListener((position) -> {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, +position);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                Date dateForecast = cal.getTime();
                String resultDate = simpleDateFormat.format(dateForecast);
                Intent intent = new Intent(getApplicationContext(), OneDayWeather.class);
                intent.putExtra(OneDayWeather.EXTRA_WEATHER, resultDate);
                startActivity(intent);
            });
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
                dial.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTemp(float tempToday){
        temp.setText(this.getString(R.string.temp_value, tempToday));
    }

    @Override
    public void setName(String title){
        ctl.setTitle(title);
    }

    @Override
    public void setIcon(String path){
        Picasso.with(this).load(Constants.iconPath + path + ".png")
                .into(icon);
    }

    @Override
    public void setDescription(String description) {
        this.description.setText(description);
    }
}





































