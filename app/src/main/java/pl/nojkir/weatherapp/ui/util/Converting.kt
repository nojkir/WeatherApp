package pl.nojkir.weatherapp.ui.util

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import pl.nojkir.weatherapp.R


    fun setImageResource(imageView: ImageView, url:String){
        when(url){
            "01d" -> imageView.setImageResource(R.drawable.ic_01d)
            "01n" -> imageView.setImageResource(R.drawable.ic_01n)
            "02d" -> imageView.setImageResource(R.drawable.ic_02d)
            "02n" -> imageView.setImageResource(R.drawable.ic_02n)
            "03d" -> imageView.setImageResource(R.drawable.ic_03d)
            "03n" -> imageView.setImageResource(R.drawable.ic_03n)
            "04d" -> imageView.setImageResource(R.drawable.ic_04d)
            "04n" -> imageView.setImageResource(R.drawable.ic_04n)
            "09d" -> imageView.setImageResource(R.drawable.ic_09d)
            "09n" -> imageView.setImageResource(R.drawable.ic_09n)
            "10d" -> imageView.setImageResource(R.drawable.ic_10d)
            "10n" -> imageView.setImageResource(R.drawable.ic_10n)
            "11d" -> imageView.setImageResource(R.drawable.ic_11d)
            "11n" -> imageView.setImageResource(R.drawable.ic_11n)
            "13d" -> imageView.setImageResource(R.drawable.ic_13d)
            "13n" -> imageView.setImageResource(R.drawable.ic_13n)
            "50d" -> imageView.setImageResource(R.drawable.ic_50d)
            "50n" -> imageView.setImageResource(R.drawable.ic_50n)

        }
    }

fun setBackgroundResource(constraintLayout: ConstraintLayout, url:String){

    when(url){

        "01d" -> constraintLayout.setBackgroundResource(R.drawable.background_sunny_weather)
        "01n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "02d" -> constraintLayout.setBackgroundResource(R.drawable.background_cloudly_weather)
        "02n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "03d" -> constraintLayout.setBackgroundResource(R.drawable.background_cloudly_weather)
        "03n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "04d" -> constraintLayout.setBackgroundResource(R.drawable.background_cloudly_weather)
        "04n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "09d" -> constraintLayout.setBackgroundResource(R.drawable.background_rainy_weather)
        "09n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "10d" -> constraintLayout.setBackgroundResource(R.drawable.background_rainy_weather)
        "10n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "11d" -> constraintLayout.setBackgroundResource(R.drawable.background_rainy_weather)
        "11n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "13d" -> constraintLayout.setBackgroundResource(R.drawable.background_snowy_weather)
        "13n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)
        "50d" -> constraintLayout.setBackgroundResource(R.drawable.background_snowy_weather)
        "50n" -> constraintLayout.setBackgroundResource(R.drawable.background_night)


    }

}
