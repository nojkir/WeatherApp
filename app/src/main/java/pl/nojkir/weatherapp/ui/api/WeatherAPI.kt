package pl.nojkir.weatherapp.ui.api

import pl.nojkir.weatherapp.models.currentWeather.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather?")
   suspend fun getWeatherByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") language: String)
            : Response<CurrentWeatherResponse>

    @GET("weather?")
    suspend fun getWeatherByCoordinates(
        @Query("lat") latitude: String,
        @Query ("lon") longitude: String,
        @Query ("appid") apiKey: String,
        @Query ("units") units: String,
        @Query ("lang") language: String
    ) :  Response<CurrentWeatherResponse>


}