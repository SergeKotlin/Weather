package com.android1.weather.model

import com.android1.weather.model.entities.Weather

sealed class AppState { // "запечатанный класс", гибкий enum
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState() // не содержит данных
}
