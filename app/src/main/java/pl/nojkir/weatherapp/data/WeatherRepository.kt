package pl.nojkir.weatherapp.data

import pl.nojkir.weatherapp.ui.api.WeatherAPI
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherAPI: WeatherAPI){

   suspend fun getWeatherByCityName(
        cityname: String,
        apiKey: String,
        units: String,
        language: String
    ) = weatherAPI.getWeatherByCityName(cityname, apiKey, units, language)

}