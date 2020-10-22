package pl.nojkir.weatherapp.models.dailyWeather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)