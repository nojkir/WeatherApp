package pl.nojkir.weatherapp.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.nojkir.weatherapp.data.WeatherRepository
import pl.nojkir.weatherapp.models.WeatherResponse
import pl.nojkir.weatherapp.ui.util.Constants
import pl.nojkir.weatherapp.ui.util.Resource
import retrofit2.Response

class CityWeatherViewModel @ViewModelInject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel(){

    init {
        getWeatherByCityName("Tokio","f180eb8998a971182830813353320521", "metric", "pl")
    }

    val weather : MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()


    fun getWeatherByCityName(cityName: String, apiKey: String, units: String ,language: String) = viewModelScope.launch {

        val response = weatherRepository.getWeatherByCityName(
            cityName, apiKey, units, language)
        weather.postValue(handleWeatherResponse(response))
    }


    private fun handleWeatherResponse(response: Response<WeatherResponse>) : Resource<WeatherResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

}