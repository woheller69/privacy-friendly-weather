package org.secuso.privacyfriendlyweather.weather_api;

import android.content.Context;

import org.secuso.privacyfriendlyweather.http.HttpRequestType;
import org.secuso.privacyfriendlyweather.http.IHttpRequest;
import org.secuso.privacyfriendlyweather.http.VolleyHttpRequest;
import org.secuso.privacyfriendlyweather.orm.City;
import org.secuso.privacyfriendlyweather.orm.CityToWatch;
import org.secuso.privacyfriendlyweather.orm.DatabaseHelper;

import java.util.List;

/**
 * This class provides the functionality for making and processing HTTP requests to the
 * OpenWeatherMap to retrieve the latest weather data for all stored cities.
 */
public class OwmHttpRequestForForecast extends OwmHttpRequest implements IHttpRequestForForecast {

    private Context context;
    private DatabaseHelper dbHelper;

    /**
     * @param context
     * @param dbHelper
     */
    public OwmHttpRequestForForecast(Context context, DatabaseHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    /**
     * @see IHttpRequestForForecast#perform(City)
     */
    @Override
    public void perform(City city) {
        IHttpRequest httpRequest = new VolleyHttpRequest(context);
        final String URL = getUrlForQueryingForecast(city.getCityId());
        httpRequest.make(URL, HttpRequestType.GET, new ProcessOwmForecastRequest(context, dbHelper));
    }
}