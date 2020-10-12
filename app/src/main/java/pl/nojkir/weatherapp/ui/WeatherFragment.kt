package pl.nojkir.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.databinding.FragmentWeatherBinding
import pl.nojkir.weatherapp.ui.util.Resource
import pl.nojkir.weatherapp.ui.viewModels.CityWeatherViewModel
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private var _binding : FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel : CityWeatherViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        viewModel.weather.observe(viewLifecycleOwner, Observer {response ->
           when(response){
               is Resource.Success -> {
                   binding.textViewCityName.text = response.data?.name
                   binding.textViewSunrise.text = response.data?.sys?.sunrise?.toLong()?.let {
                       timeConverter(
                           it , response.data.timezone.toLong()
                       )
                   }
                   binding.textViewSunset.text = response.data?.sys?.sunset?.toLong()?.let {
                       timeConverter(
                           it, response.data.timezone.toLong()
                       )
                   }
                   binding.textViewPressure.text = response.data?.main?.pressure.toString()
                   binding.textViewWind.text = response.data?.wind?.speed.toString()
                   binding.textViewTemperature.text = response.data?.main?.temp?.let { Math.round(it).toString() } + " °C"
                   binding.textViewDescription.text = response.data?.weather?.get(0)?.description

               }
           }
        })

        return binding.root



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun timeConverter(time: Long, timezone: Long): String {
        var converter = SimpleDateFormat("hh:mm a")
        var convertedTime = converter.format(Date(time * 1000 + timezone * 1000))

        return convertedTime

    }
    }
