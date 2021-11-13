package com.example.weatherappvolley;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button getWeatherButton;
    EditText weatherCityName;
    ListView listView;
    String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference buttons and text of user interface
        getWeatherButton = findViewById(R.id.get_weather_button);
        weatherCityName = findViewById(R.id.text_name_city);
        listView = findViewById(R.id.listview);


        cityName= "detroit";
        ///set default city
        weatherCityName.setText("Detroit");

        //create an instance of WeatherDataService
        WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        //set on button click listener
        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: "+ weatherCityName.getText().toString());

                //get value input from user. get the city text
                cityName = weatherCityName.getText().toString();

                //call getForecast method
                weatherDataService.getForecast(new WeatherDataService.ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Log.d(TAG, "onError: "+ message.toString());
                    }

                    @Override
                    public void onResponse(List<WeatherReport> weather) {
                        Log.d(TAG, "onResponse: " + weather.toString());
                        Log.d(TAG, "onResponse: "+ weather.get(0));
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weather);
                        listView.setAdapter(arrayAdapter);
                    }
                }, cityName);
            }
        });


    }
}