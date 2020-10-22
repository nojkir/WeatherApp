package pl.nojkir.weatherapp.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.nojkir.weatherapp.data.ForecastWeatherRepository
import pl.nojkir.weatherapp.models.forecastWeather.ForecastWeatherResponse
import pl.nojkir.weatherapp.ui.util.Resource
import retrofit2.Response

class ForecastWeatherViewModel @ViewModelInject constructor(
    private val forecastWeatherRepository: ForecastWeatherRepository
) : ViewModel(){

    var forecastWeather : MutableLiveData<Resource<ForecastWeatherResponse>> = MutableLiveData()

    fun getForecastForCityName(cityName: String, apiKey: String, numberOfDays: Int, units: String, language: String) = viewModelScope.launch {
        val response = forecastWeatherRepository.getForecastByCityName(cityName, apiKey, numberOfDays, units, language)

            forecastWeather.postValue(handleForecastResponse(response))
    }

    fun handleForecastResponse(response: Response<ForecastWeatherResponse>) : Resource<ForecastWeatherResponse> {
        if (response.isSuccessful){
            response.body()?.let { forecastWeatherResponse ->
                return Resource.Success(forecastWeatherResponse)
            }
        }

        return Resource.Error(response.message())
    }



}