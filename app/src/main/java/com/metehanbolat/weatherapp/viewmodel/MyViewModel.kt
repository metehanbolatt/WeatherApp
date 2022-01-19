package com.metehanbolat.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.metehanbolat.weatherapp.model.WeatherModel
import com.metehanbolat.weatherapp.service.WeatherAPIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MyViewModel : ViewModel(){

    private val weatherAPIService = WeatherAPIService()
    private val disposable = CompositeDisposable()
    val weathers = MutableLiveData<WeatherModel>()

    fun getDataAPI(city: String){
        disposable.add(
            weatherAPIService.getData(city)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>(){
                    override fun onSuccess(t: WeatherModel) {
                        weathers.value = t
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }
}