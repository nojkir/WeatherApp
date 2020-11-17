package pl.nojkir.weatherapp.models.oneCallWeather



data class OneCallResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: Current,
    val weather: List<WeatherOneCall>,
    val minutely: List<MinutelyOneCall>,
    val hourly: List<HourlyOneCall>,
    val daily: List<DailyOneCall>


    ) {

}

data class WeatherOneCall(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Current(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val uvi: Double,
    val cloud: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int
)

data class MinutelyOneCall(
    val dt: Int,
    val precipitation: Double
)

data class HourlyOneCall(
    val dt: Int,
    val temp: Double,
    val feels_like: Double,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val clouds: Int,
    val visibility: Int,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: List<WeatherOneCall>
)

data class DailyOneCall(

    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: TempOneCall,
    val pressure: Int,
    val humidity: Int,
    val weather: List<WeatherOneCall>

)
data class TempOneCall(

    val day: Double,
    val min: Double,
    val max: Double,
    val eve: Double,
    val morn: Double
)

