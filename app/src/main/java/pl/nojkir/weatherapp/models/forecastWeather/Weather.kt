package pl.nojkir.weatherapp.models.forecastWeather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)