package pl.nojkir.weatherapp.ui.api


import pl.nojkir.weatherapp.models.oneCallWeather.OneCallResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastWeatherAPI  {

    @GET("onecall?")
    suspend fun getForecastByCityName(
    @Query("appid") apiKey: String,
    @Query ("lat") latitude: String,
    @Query("lon") longitude: String,
    @Query("units") units: String,
    @Query ("lang") language: String
    ) : Response <OneCallResponse>


}