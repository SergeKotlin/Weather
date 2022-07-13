package com.android1.weather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android1.weather.model.AppState
import com.android1.weather.model.RepositoryImpl
import com.android1.weather.model.entities.Weather
import java.lang.Thread.sleep

class MainViewModel(private val liveDataToObserve: MutableLiveData<AppState> =
        MutableLiveData()) : ViewModel(){
        /* MutableLiveData - как почтальон с посылкой. Генерит эвенты, когда меняются данные внутри MutableLiveData
        LiveData - позволяет обращаться к себе только на чтение. Они связаны, MutableLiveData наследует LiveData. */

    private val repository: RepositoryImpl = RepositoryImpl()

    fun getData(): LiveData<AppState> {
        getDataFromLocalSource()
        /* liveDataToObserve.value = "" Эквивалентно setValue(). К примеру, эта строка создат эвент об изменении LiveData для её подписчиков
        .postValue() синхронизируется с потоком UI (основным), .value (или .setValue) обновляет данные прямо там, где лежит, в рабочем потоке. */
        return liveDataToObserve
    }

    fun getLiveData() = liveDataToObserve

    fun getWeather() = getDataFromLocalSource()

    fun getWeatherFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() { // Имитирует запрос к БД (или ещё какому-то источнику данных в приложении)
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(2000) // ... имитация какой-то работы..
            liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage())) // Any аналог Object в Java - т.е "всё что угодно"
            // Прим.: при множественном вызове изменений данных вызовется только одно из них (типа защиты от DDoS)
        }.start()
    }
}