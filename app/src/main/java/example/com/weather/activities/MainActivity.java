package example.com.weather.activities;


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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import example.com.weather.Constants;
import example.com.weather.DialogFragment;
import example.com.weather.R;
import example.com.weather.adapters.AdapterWeather;
import example.com.weather.forecast.ForecastObj;
import example.com.weather.rest.RequestWeather;
import example.com.weather.rest.ServiceGenerator;
import example.com.weather.utility.DateFormatter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DialogFragment.FrameListener {

    private Toolbar toolbar;
    private DialogFragment dialogFragment;
    private EditText editText;
    private String cityName = "Tambov";
    private List<example.com.weather.forecast.List> dateWeather;
    private ForecastObj jSonResponse;
    private TextView todayWeather;
    private RecyclerView recyclerView;
    private AdapterWeather adapter;
    private TextView rainTitle;
    private ImageView titleIcon;
    private DateFormatter dateFormatter = new DateFormatter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            cityName = savedInstanceState.getString("cityName");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogFragment = new DialogFragment();
        recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        todayWeather = (TextView) findViewById(R.id.temp_today);
        rainTitle = (TextView) findViewById(R.id.rain_in_title);
        titleIcon = (ImageView) findViewById(R.id.icon_title);
        toolbar = (Toolbar) findViewById(R.id.i_toolbar);
        setSupportActionBar(toolbar);

        RequestWeather requestWeather = ServiceGenerator.create(RequestWeather.class);
        requestWeather.getWeather(cityName, Constants.LANG, Constants.DIMENSION, Constants.KEY).enqueue(new Callback<ForecastObj>() {
            @Override
            public void onResponse(Call<ForecastObj> call, Response<ForecastObj> response) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                jSonResponse = response.body();
                if (jSonResponse != null) {
                    dateWeather = jSonResponse.getList();
                    adapter = new AdapterWeather(getApplicationContext(), dateWeather);
                    recyclerView.setAdapter(adapter);
                    todayWeather.setText(getString(R.string.temp_value, dateWeather.get(0).getTemp().getDay()));
                    dateFormatter.pushDate(0, dateWeather, getApplicationContext(), titleIcon, rainTitle);

                    CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
                    ctl.setTitle(jSonResponse.getCity().getName());
                    ctl.setExpandedTitleColor(Color.BLACK);


                    adapter.setListener(new AdapterWeather.Listener() {
                        public void onClick(int position) {
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE, +position);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                            Date dateForecast = cal.getTime();
                            String resultDate = simpleDateFormat.format(dateForecast);
                            Intent intent = new Intent(getApplicationContext(), OneDayWeather.class);
                            intent.putExtra(OneDayWeather.EXTRA_WEATHER, resultDate);
                            intent.putExtra("city", cityName);
                            startActivity(intent);
                        }
                    });
                } else {
                    cityName = "Tambov";
                    recreate();
                }
            }

            @Override
            public void onFailure(Call<ForecastObj> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
            }
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
                dialogFragment.show(getFragmentManager(), "dialogFragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        editText = (EditText) dialog.getDialog().findViewById(R.id.edit_city_title);
        cityName = editText.getText().toString();
        recreate();
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("cityName", cityName);
        super.onSaveInstanceState(savedInstanceState);
    }
}





































