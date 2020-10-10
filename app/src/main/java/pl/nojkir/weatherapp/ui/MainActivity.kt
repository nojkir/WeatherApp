package pl.nojkir.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.nojkir.weatherapp.R
import pl.nojkir.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}