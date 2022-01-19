package com.metehanbolat.weatherapp.service

import com.metehanbolat.weatherapp.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    // https://api.openweathermap.org/data/2.5/weather?q=moskova&lang=tr&units=metric&appid=d4d9afb95804a8fe4e2aacc6e2338715
    @GET("weather?&lang=tr&units=metric&appid=d4d9afb95804a8fe4e2aacc6e2338715")
    fun getWeather(@Query("q") city: String) : Single<WeatherModel>

}