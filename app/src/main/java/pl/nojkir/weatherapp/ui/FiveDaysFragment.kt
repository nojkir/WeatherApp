package pl.nojkir.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fivedays_fragment.*
import kotlinx.android.synthetic.main.weather_item.*
import pl.nojkir.adapters.FiveDaysAdapter
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.databinding.FivedaysFragmentBinding
import pl.nojkir.weatherapp.models.oneCallWeather.DailyOneCall
import pl.nojkir.weatherapp.models.oneCallWeather.OneCallResponse
import pl.nojkir.weatherapp.ui.util.*
import pl.nojkir.weatherapp.ui.viewModels.ForecastWeatherViewModel

@AndroidEntryPoint
class FiveDaysFragment : Fragment(R.layout.fivedays_fragment) {

    private var _binding: FivedaysFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var fiveDaysAdapter: FiveDaysAdapter
    private lateinit var fiveDaysList: MutableList<DailyOneCall>
    private var amountOfDays = 5


    private val viewModel: ForecastWeatherViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FivedaysFragmentBinding.bind(view)

        setupRecyclerView()


            viewModel.forecastWeather.observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { oneCallResponse ->

                            handlingUI(oneCallResponse, 0, binding.fiveDaysTemperature, binding.fiveDays1day, binding.fiveDays1date, binding.imageView2)
                            handlingUI(oneCallResponse, 1, binding.fiveDaysTemperature2, binding.fiveDays2day, binding.fiveDays2date, binding.imageView3)
                            handlingUI(oneCallResponse, 2, binding.fiveDaysTemperature3, binding.fiveDays3day, binding.fiveDays3date, binding.imageView4)
                            handlingUI(oneCallResponse, 3, binding.fiveDaysTemperature4, binding.fiveDays4day, binding.fiveDays4date, binding.imageView5)
                            handlingUI(oneCallResponse, 4, binding.fiveDaysTemperature5, binding.fiveDays5day, binding.fiveDays5date, binding.imageView6)

                            fiveDaysList = mutableListOf()
                            var position = 0
                            while (position < amountOfDays) {
                                fiveDaysList.add(oneCallResponse.daily[position])
                                position++
                            }
                            fiveDaysAdapter.timeZone = oneCallResponse.timezone_offset.toLong()
                            fiveDaysAdapter.differ.submitList(fiveDaysList)



                        }
                    }
                    is Resource.Error -> {
                        Log.d("FiveDaysFragment", response.messge)
                    }

                    is Resource.Loading -> {
                        Log.d("FiveDaysFragment", response.messge)

                    }
                }

            })

        setHasOptionsMenu(true)
    }




    private fun setupRecyclerView() {
        fiveDaysAdapter = FiveDaysAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = fiveDaysAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))


        }
    }

    private fun handlingUI(oneCallResponse: OneCallResponse, position: Int, temperature: TextView, day: TextView, date: TextView, image: ImageView ){


        day.text = timeConverterToDayOfWeek(oneCallResponse.daily[position].dt.toLong(), oneCallResponse.timezone_offset.toLong())
        date.text = timeConverterToDate(oneCallResponse.daily[position].dt.toLong(), oneCallResponse.timezone_offset.toLong())
        temperature.text = oneCallResponse.daily[position].temp.day.toString()
        setImageResource(image,oneCallResponse.daily[position].weather[0].icon )

    }

}
