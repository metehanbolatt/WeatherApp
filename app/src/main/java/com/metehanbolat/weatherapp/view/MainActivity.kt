package com.metehanbolat.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.metehanbolat.weatherapp.databinding.ActivityMainBinding
import com.metehanbolat.weatherapp.model.WeatherModel
import com.metehanbolat.weatherapp.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        binding.searchButton.setOnClickListener {
            viewModel.getDataAPI(binding.cityEdit.text.toString())
        }

        viewModel.weathers.observe(this) { weather ->

            binding.city.text = weather.name
            binding.feelsLike.text = "${weather.main.feels_like}°C"
            binding.minTemperature.text = "${weather.main.temp_min}°C"
            binding.maxTemperature.text = "${weather.main.temp_max}°C"
            binding.temperature.text = "${weather.main.temp}°C"
            binding.weatherDetail.text = weather.weather[0].description
            val imageUrl = "https://openweathermap.org/img/wn/" + weather.weather[0].icon + "@2x.png"
            Glide.with(this).load(imageUrl).into(binding.weatherImage)
        }
    }
}