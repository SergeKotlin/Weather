package com.android1.weather.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android1.weather.domain.Repository
import com.android1.weather.data.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    /* MutableLiveData - как почтальон с посылкой. Генерит эвенты, когда меняются данные внутри MutableLiveData
    LiveData - позволяет обращаться к себе только на чтение. Они связаны, MutableLiveData наследует LiveData. */
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel(){

    /*fun getData(): LiveData<AppState> { // LiveData для подписчиков
        getDataFromLocalSource()
        return liveDataToObserve
    }*/

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getWeatherFromLocalSource(isWorld = false)

    fun getWeatherFromLocalSourceWorld() = getWeatherFromLocalSource(isWorld = true)

    private fun getWeatherFromLocalSource(isWorld: Boolean) { // Имитирует запрос к БД (или ещё какому-то источнику данных в приложении)
        liveDataToObserve.value = AppState.Loading
        /* liveDataToObserve.value = "" Эквивалентно setValue(). К примеру, эта строка создат эвент об изменении LiveData для её подписчиков
        .postValue() синхронизируется с потоком UI (основным), .value (или .setValue) обновляет данные прямо там, где лежит, в рабочем потоке. */
        Thread {
            sleep(2000) // ... имитация какой-то работы..
            liveDataToObserve.postValue(AppState.Success( if (!isWorld)
                repository.getWeatherFromLocalStorageRus() else
                repository.getWeatherFromLocalStorageWorld()))
            // Any аналог Object в Java - т.е "всё что угодно"
            // Прим.: при множественном вызове изменений данных вызовется только одно из них (типа защиты от DDoS)
        }.start()
    }

    fun getWeatherFromRemoteSource() = getWeatherFromLocalSource(isWorld = false) // Костыль
}