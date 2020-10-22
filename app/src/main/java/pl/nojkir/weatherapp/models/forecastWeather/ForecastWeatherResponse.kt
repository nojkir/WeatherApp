package pl.nojkir.weatherapp.models.forecastWeather

data class ForecastWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastWeather>,
    val message: Int
)