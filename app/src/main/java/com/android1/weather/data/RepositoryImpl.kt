package com.android1.weather.data

import com.android1.weather.domain.Repository
import com.android1.weather.data.model.entities.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromLocalStorage() = Weather()

    override fun getWeatherFromServer() = Weather()
}