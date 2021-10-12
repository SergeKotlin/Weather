package com.android1.weather.model.entities

data class Weather(
        val city: City = getDefaultCity(),
        val temperature: Int = 0,
        val feelslLike: Int = 0
)

fun getDefaultCity() = City("Москва", 55.755826, 37.617299900000035)