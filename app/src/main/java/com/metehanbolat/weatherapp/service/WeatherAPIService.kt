package com.metehanbolat.weatherapp.service

import com.metehanbolat.weatherapp.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIService {

    private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val retro = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)

    fun getData(city: String) : Single<WeatherModel> {
        return retro.getWeather(city)
    }
}