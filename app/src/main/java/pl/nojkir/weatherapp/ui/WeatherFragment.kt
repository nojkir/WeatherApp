package pl.nojkir.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.databinding.FragmentWeatherBinding


@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private var _binding : FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }
