package pl.nojkir.weatherapp.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.nojkir.weatherapp.data.ForecastWeatherRepository
import pl.nojkir.weatherapp.models.oneCallWeather.OneCallResponse
import pl.nojkir.weatherapp.ui.util.Resource
import retrofit2.Response

class ForecastWeatherViewModel @ViewModelInject constructor(
    private val forecastWeatherRepository: ForecastWeatherRepository
) : ViewModel(){

    init {
        getForecastByCoordinates(
            "3801ab2cbebba5f25d7fcd4d73c46273","50.2974884" ,"18.9545728", "metric", "pl")
    }



    var forecastWeather : MutableLiveData <Resource<OneCallResponse>> = MutableLiveData()

    private fun getForecastByCoordinates(apiKey: String, latitude: String, longitude: String, units: String, language: String) = viewModelScope.launch {
        val response = forecastWeatherRepository.getForecastByCityName( apiKey,latitude, longitude,  units, language)

            forecastWeather.postValue(handleForecastResponse(response))
    }

    private fun handleForecastResponse(response: Response<OneCallResponse>) : Resource<OneCallResponse> {
        if (response.isSuccessful){
            response.body()?.let { forecastWeatherResponse ->
                return Resource.Success(forecastWeatherResponse)
            }
        }

        return Resource.Error(response.message())
    }



}