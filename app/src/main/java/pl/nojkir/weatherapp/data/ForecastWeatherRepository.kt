package pl.nojkir.weatherapp.data

import pl.nojkir.weatherapp.ui.api.ForecastWeatherAPI
import javax.inject.Inject

class ForecastWeatherRepository @Inject constructor(
    private val forecastWeatherAPI: ForecastWeatherAPI
) {

    suspend fun getForecastByCityName(
        apiKey: String,
        longitude: String,
        latitude: String,
        units: String,
        language: String
    ) = forecastWeatherAPI.getForecastByCityName( apiKey, longitude,latitude, units, language)

}