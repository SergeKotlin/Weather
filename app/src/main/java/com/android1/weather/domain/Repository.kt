package com.android1.weather.domain

import com.android1.weather.data.model.entities.Weather

interface Repository {
    fun getWeatherFromLocalStorage(): Weather
    fun getWeatherFromServer(): Weather
}