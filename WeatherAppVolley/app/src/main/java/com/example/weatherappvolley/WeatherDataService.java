package com.example.weatherappvolley;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    Context context;
    String url;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    //for a callback
    public interface ForecastByIDResponse {
        void onError(String message);

        //you can change the parameters to string if you know you are waiting for a string to comeback
        void onResponse(List<WeatherReport> weatherReportModel);
    }
    public void getForecast(ForecastByIDResponse forecastByIDResponse, String cityName){
        List<WeatherReport> report = new ArrayList<>();

        //get json object
        url = "https://api.weatherapi.com/v1/current.json?key=3e3ba37cf1f44e65b24144454211211&q=" +cityName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(TAG, "onResponse:  "+jsonObject.toString());


                //we get a list of jsonojbects
                //get the first one
                try {
                    JSONObject current_weather = jsonObject.getJSONObject("current");

                    Log.d(TAG, "onResponse: in the hole "+current_weather.toString());

                        Log.d(TAG, "onResponse: getting item "+current_weather.getString("last_updated"));
                        WeatherReport weatherReportModel = new WeatherReport();

                        weatherReportModel.setTemp_f(current_weather.getDouble("temp_f"));
                        weatherReportModel.setTemp_c((float) current_weather.getDouble("temp_c"));
                        weatherReportModel.setHumidity(current_weather.getDouble("humidity"));
                        weatherReportModel.setWind_mph((float) current_weather.getDouble("wind_mph"));
                        weatherReportModel.setLast_updated(current_weather.getString("last_updated"));

                        //add it to the arraylist
                        report.add(weatherReportModel);


                    forecastByIDResponse.onResponse(report);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, "onResponse:  "+volleyError.toString());

            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);

    }
}
