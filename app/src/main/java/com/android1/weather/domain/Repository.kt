package com.android1.weather.domain

import com.android1.weather.data.model.entities.Weather

interface Repository {
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>

    fun getWeatherFromServer(): Weather
}