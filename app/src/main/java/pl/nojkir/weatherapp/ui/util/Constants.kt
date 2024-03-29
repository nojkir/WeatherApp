package pl.nojkir.weatherapp.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.core.content.res.TypedArrayUtils.getString


import pl.nojkir.weatherapp.R

class Constants {
    companion object {
        val CURRENT_WEATHER_BASE_URL = Resources.getSystem().getString(R.string.BASE_URL)
        val FORECAST_WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?"
        val API_KEY = Resources.getSystem().getString(R.string.WEATHER_API_KEY)


    }
}