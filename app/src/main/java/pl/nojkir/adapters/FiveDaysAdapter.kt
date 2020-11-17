package pl.nojkir.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_item.view.*
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.models.oneCallWeather.DailyOneCall
import pl.nojkir.weatherapp.ui.util.setImageResource
import pl.nojkir.weatherapp.ui.util.timeConverterToDate
import pl.nojkir.weatherapp.ui.util.timeConverterToMinutes

class FiveDaysAdapter(

) : RecyclerView.Adapter<FiveDaysAdapter.FiveDaysViewHolder>() {
        var timeZone: Long? = null

    inner class FiveDaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<DailyOneCall>() {
        override fun areItemsTheSame(oldItem: DailyOneCall, newItem: DailyOneCall): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: DailyOneCall, newItem: DailyOneCall): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDaysViewHolder {
        return FiveDaysViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FiveDaysViewHolder, position: Int) {
        val oneDay = differ.currentList[position]


        holder.itemView.apply {

            tV_Date.text = timeZone?.let { timeConverterToDate(oneDay.dt.toLong(), it) }
            tV_sunset.text =  timeConverterToMinutes(oneDay.sunset.toLong(), timeZone!!)
            tV_sunrise.text = timeConverterToMinutes(oneDay.sunrise.toLong(), timeZone!!)
            tV_temperature.text = oneDay.temp.day.toString()
            tV_humidity.text = oneDay.humidity.toString()
            tV_pressure.text = oneDay.pressure.toString()
            setImageResource(
                imageView_weatherIcon,
                oneDay.weather[0].icon.toString()
            )
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}