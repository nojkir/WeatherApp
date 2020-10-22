package pl.nojkir.weatherapp.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.nojkir.weatherapp.data.CurrentWeatherRepository
import pl.nojkir.weatherapp.models.currentWeather.CurrentWeatherResponse
import pl.nojkir.weatherapp.ui.util.Resource
import retrofit2.Response

class CurrentWeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: CurrentWeatherRepository
) : ViewModel(){

    var latitude: String = ""
    var longitude: String = ""
    var apiKey = "f180eb8998a971182830813353320521"
    var units = "metric"
    var language = "pl"
    var cityName = "Chorzów"

    init {
//        getWeatherByCityName(cityName, apiKey, units, language)
        getWeatherByCoordinates(latitude, longitude, apiKey, units, language)

    }

    val currentWeather : MutableLiveData<Resource<CurrentWeatherResponse>> = MutableLiveData()
    val coordCurrentWeather: MutableLiveData<Resource<CurrentWeatherResponse>> = MutableLiveData()


    fun getWeatherByCityName(cityName: String, apiKey: String, units: String ,language: String) = viewModelScope.launch {

        val response = weatherRepository.getWeatherByCityName(
            cityName, apiKey, units, language)
        currentWeather.postValue(handleWeatherResponse(response))
    }
    fun getWeatherByCoordinates(latitude: String, longitude: String, apiKey: String, units: String, language: String) = viewModelScope.launch {
        val response = weatherRepository.getWeatherByCoordinates(
            latitude, longitude, apiKey, units, language
        )

        coordCurrentWeather.postValue(handleWeatherResponse(response))
    }


    private fun handleWeatherResponse(responseCurrent: Response<CurrentWeatherResponse>) : Resource<CurrentWeatherResponse>{
        if (responseCurrent.isSuccessful){
            responseCurrent.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(responseCurrent.message())
    }

}