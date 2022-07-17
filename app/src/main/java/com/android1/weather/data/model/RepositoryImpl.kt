package com.android1.weather.data.model

import com.android1.weather.domain.Repository
import com.android1.weather.data.model.entities.Weather
import com.android1.weather.data.model.entities.getRussianCities
import com.android1.weather.data.model.entities.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }

    override fun getWeatherFromServer() = Weather()
}