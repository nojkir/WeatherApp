package pl.nojkir.weatherapp.ui.api

import pl.nojkir.weatherapp.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?")
   suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("lang") language: String)
            : Response<WeatherResponse>


}