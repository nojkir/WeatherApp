package pl.nojkir.weatherapp.models.dailyWeather

data class DailyWeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyWeather>,
    val message: Int
)