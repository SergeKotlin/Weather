package com.android1.weather.ui.viewModel

import com.android1.weather.data.model.entities.Weather

sealed class AppState { // "запечатанный класс", гибкий enum
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState() // не содержит данных
}
