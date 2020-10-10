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
        getWeatherByCityName("Katowice", Constants.API_KEY, "pl")
    }

    val weather : MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()


    fun getWeatherByCityName(cityName: String, apiKey: String, language: String) = viewModelScope.launch {
        weather.postValue((Resource.Loading()))
        val response = weatherRepository.getWeatherByCityName(
            cityName, apiKey, language)
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