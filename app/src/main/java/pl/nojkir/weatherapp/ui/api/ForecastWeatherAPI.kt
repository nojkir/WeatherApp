package pl.nojkir.weatherapp.ui.api

import pl.nojkir.weatherapp.models.forecastWeather.ForecastWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastWeatherAPI  {

    @GET("forecast/daily?")
    suspend fun getForecastByCityName(
    @Query("q") cityName: String,
    @Query("appid") apiKey: String,
    @Query ("cnt") numberOfDays: Int,
    @Query("units") units: String,
    @Query ("lang") language: String
    ) : Response <ForecastWeatherResponse>


}