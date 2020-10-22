package pl.nojkir.weatherapp.data

import pl.nojkir.weatherapp.ui.api.CurrentWeatherAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentWeatherRepository @Inject constructor(private val currentWeatherAPI: CurrentWeatherAPI){

   suspend fun getWeatherByCityName(
        cityname: String,
        apiKey: String,
        units: String,
        language: String
    ) = currentWeatherAPI.getWeatherByCityName(cityname, apiKey, units, language)

    suspend fun getWeatherByCoordinates(
        latitude: String,
        longitude: String,
        apiKey: String,
        units: String,
        language: String
    ) = currentWeatherAPI.getWeatherByCoordinates(latitude, longitude, apiKey, units, language)

}