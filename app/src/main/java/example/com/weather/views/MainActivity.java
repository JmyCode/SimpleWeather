package example.com.weather.views;


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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import example.com.weather.R;

import example.com.weather.controllers.AdapterWeather;
import example.com.weather.controllers.Presenter;

public class MainActivity extends AppCompatActivity implements DialogFragment.FrameListener {

    private Toolbar toolbar;
    private DialogFragment dialogFragment;
    private EditText editText;
    private String cityName = "Tambov";
    private RecyclerView recyclerView;
    private AdapterWeather adapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogFragment = new DialogFragment();
        toolbar = (Toolbar) findViewById(R.id.i_toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterWeather(this);
        recyclerView.setAdapter(adapter);

        presenter = new Presenter(getWindow().getDecorView(), this);

        config();


    }

    protected void onStart(){
        super.onStart();
        adapter.updateWeather();
        presenter.getTitle();
        presenter.setView();


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
                intent.putExtra("city", cityName);
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
    }





































