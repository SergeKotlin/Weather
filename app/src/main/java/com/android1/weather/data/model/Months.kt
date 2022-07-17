package com.android1.weather.data.model

sealed class Months { // "запечатанный класс", гибкий enum
    data class January(val monthIndex: Int, val shortForm: String) : Months()
    data class February(val monthIndex: Int, val shortForm: String, val noOfDays: Int) : Months()
    data class March(val monthIndex: Int, val shortForm: String) : Months()
    data class April(val monthIndex: Int, val shortForm: String) : Months()
    data class May(val monthIndex: Int, val shortForm: String) : Months()
    data class June(val monthIndex: Int, val shortForm: String) : Months()
    data class July(val monthIndex: Int, val shortForm: String) : Months()
    data class August(val monthIndex: Int, val shortForm: String) : Months()
    data class September(val monthIndex: Int, val shortForm: String) : Months()
    data class October(val monthIndex: Int, val shortForm: String) : Months()
    data class November(val monthIndex: Int, val shortForm: String) : Months()
    data class December(val monthIndex: Int, val shortForm: String) : Months()
}
