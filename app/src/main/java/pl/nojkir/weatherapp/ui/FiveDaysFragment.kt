package pl.nojkir.weatherapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.databinding.FragmentWeatherBinding

class FiveDaysFragment : Fragment(R.layout.fivedays_fragment) {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeatherBinding.bind(view)


    }
}