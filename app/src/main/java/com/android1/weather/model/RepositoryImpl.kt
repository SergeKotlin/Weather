package com.android1.weather.model

import com.android1.weather.model.entities.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorage() = Weather()
}