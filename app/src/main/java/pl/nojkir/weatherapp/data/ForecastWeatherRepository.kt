package pl.nojkir.weatherapp.data

import pl.nojkir.weatherapp.ui.api.ForecastWeatherAPI
import javax.inject.Inject

class ForecastWeatherRepository @Inject constructor(
    private val forecastWeatherAPI: ForecastWeatherAPI
) {

    suspend fun getForecastByCityName(
        cityName: String,
        apiKey: String,
        numberOfDays: Int,
        units: String,
        language: String
    ) = forecastWeatherAPI.getForecastByCityName(cityName, apiKey, numberOfDays, units, language)

}