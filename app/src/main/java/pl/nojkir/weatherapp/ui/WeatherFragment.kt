package pl.nojkir.weatherapp.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.fragment_weather.*
import pl.nojkir.repository.UserPreferencesRepository
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.databinding.FragmentWeatherBinding
import pl.nojkir.weatherapp.ui.util.*
import pl.nojkir.weatherapp.ui.viewModels.CurrentWeatherViewModel
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather), EasyPermissions.PermissionCallbacks {

    val REQUEST_CODE = 1

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrentWeatherViewModel by viewModels()
    private lateinit var location: SimpleLocation




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWeatherBinding.bind(view)

        requestPermissions()
        location = SimpleLocation(requireContext())
        if (!location.hasLocationEnabled()) {
            SimpleLocation.openSettings(requireContext());
        }
            settings()

        if (viewModel.deafultLocationEnabled) {
            viewModel.getWeatherByCityName(
                viewModel.cityName,
                viewModel.apiKey,
                viewModel.units,
                viewModel.language
            )
            viewModel.currentWeather.observe(viewLifecycleOwner,  { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.textViewCityName.text = response.data?.name
                        binding.textViewSunrise.text = response.data?.sys?.sunrise?.toLong()?.let {
                            timeConverterToMinutes(
                                it, response.data.timezone.toLong()
                            )
                        }
                        binding.textViewSunset.text = response.data?.sys?.sunset?.toLong()?.let {
                            timeConverterToMinutes(
                                it, response.data.timezone.toLong()
                            )
                        }
                        binding.textViewPressure.text = response.data?.main?.pressure.toString()
                        binding.textViewWind.text = response.data?.wind?.speed.toString()
                        binding.textViewTemperature.text = if (viewModel.units == "metric") response.data?.main?.temp?.let { Math.round(it).toString() } + "°C"
                        else response.data?.main?.temp?.let { Math.round(it).toString() } + "°F"

                        binding.textViewDescription.text = response.data?.weather?.get(0)?.description
                        setImageResource(
                            binding.imageViewWeatherIcon,
                            response.data?.weather?.get(0)?.icon.toString()
                        )
                        setBackgroundResource(
                            binding.root,
                            response.data?.weather?.get(0)?.icon.toString()
                        )

                    }
                }
            })

        }else {
            Toast.makeText(requireContext(), "Disabled ${viewModel.cityName} ", Toast.LENGTH_LONG).show()
            viewModel.longitude = location.longitude.toString()
            viewModel.latitude = location.latitude.toString()

            viewModel.getWeatherByCoordinates("50.297488", "18.954572",
                viewModel.apiKey,
                viewModel.units,
                viewModel.language
            )
            viewModel.coordCurrentWeather.observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.textViewCityName.text = response.data?.name
                        binding.textViewSunrise.text = response.data?.sys?.sunrise?.toLong()?.let {
                            timeConverterToMinutes(
                                it, response.data.timezone.toLong()
                            )
                        }
                        binding.textViewSunset.text = response.data?.sys?.sunset?.toLong()?.let {
                            timeConverterToMinutes(
                                it, response.data.timezone.toLong()
                            )
                        }
                        binding.textViewPressure.text = response.data?.main?.pressure.toString()
                        binding.textViewWind.text = response.data?.wind?.speed.toString()
                        binding.textViewTemperature.text = if (viewModel.units == "metric") response.data?.main?.temp?.let { Math.round(it).toString() } + "°C"
                        else response.data?.main?.temp?.let { Math.round(it).toString() } + "°F"

                        binding.textViewDescription.text = response.data?.weather?.get(0)?.description
                        setImageResource(
                            binding.imageViewWeatherIcon,
                            response.data?.weather?.get(0)?.icon.toString()
                        )
                        setBackgroundResource(
                            binding.root,
                            response.data?.weather?.get(0)?.icon.toString()
                        )

                    }
                    is Resource.Loading -> {
                        Log.d("WeatherFragment", response.messge)
                    }

                    is Resource.Error -> {
                        Log.d("WeatherFragment", response.messge)
                    }

                }
            })

        }






            fab_localization.setOnClickListener {
                viewModel.getWeatherByCoordinates("50.297488", "18.954572",
                    viewModel.apiKey,
                    viewModel.units,
                    viewModel.language
                )
                viewModel.coordCurrentWeather.observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.textViewCityName.text = response.data?.name
                        binding.textViewSunrise.text = response.data?.sys?.sunrise?.toLong()?.let {
                            timeConverterToMinutes(
                                it, response.data.timezone.toLong()
                            )
                        }
                        binding.textViewSunset.text = response.data?.sys?.sunset?.toLong()?.let {
                            timeConverterToMinutes(
                                it, response.data.timezone.toLong()
                            )
                        }
                        binding.textViewPressure.text = response.data?.main?.pressure.toString()
                        binding.textViewWind.text = response.data?.wind?.speed.toString()
                        binding.textViewTemperature.text = if (viewModel.units == "metric") response.data?.main?.temp?.let { Math.round(it).toString() } + "°C"
                        else response.data?.main?.temp?.let { Math.round(it).toString() } + "°F"

                        binding.textViewDescription.text = response.data?.weather?.get(0)?.description
                        setImageResource(
                            binding.imageViewWeatherIcon,
                            response.data?.weather?.get(0)?.icon.toString()
                        )
                        setBackgroundResource(
                            binding.root,
                            response.data?.weather?.get(0)?.icon.toString()
                        )

                    }
                    is Resource.Loading -> {
                        Log.d("WeatherFragment", response.messge)
                    }

                    is Resource.Error -> {
                        Log.d("WeatherFragment", response.messge)
                    }

                }
            }) }






        setHasOptionsMenu(true)
    }


    private fun settings(){
        val preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        viewModel.deafultLocationEnabled = preferences.getBoolean("default_city_check_box", false)
        val city = preferences.getString("location", "")

        viewModel.cityName = city.toString()

        viewModel.units = if (preferences.getString("units", "") == "Metric_value") "metric" else "imperial"
        viewModel.language = if (preferences.getString("language", "") == "Polish_value") "pl" else "en"
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_city_weather, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.getWeatherByCityName(
                        query,
                        viewModel.apiKey,
                        viewModel.units,
                        viewModel.language
                    )
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }


        })


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        location.beginUpdates()


    }

    override fun onPause() {
        location.endUpdates()
        super.onPause()

    }

    private fun requestPermissions() {
        if (GpsUtility.hasLocationPermissions(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app.",
                REQUEST_CODE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,

                )

        }
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else
            requestCode
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}


