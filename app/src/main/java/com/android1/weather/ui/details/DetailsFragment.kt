package com.android1.weather.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android1.weather.R
import com.android1.weather.databinding.FragmentDetailsBinding
import com.android1.weather.ui.viewModel.AppState
import com.android1.weather.data.model.entities.Weather
import com.android1.weather.ui.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {
/*
    private var _binding: FragmentDetailsBinding? = null // эта переменная существует только между методами onCreateView и onDestroyView
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        /* Обязательно обнуляем _binding в onDestroyView, чтобы избежать утечек и нежелаемого
        поведения. (В Activity ничего похожего делать не требуется) */
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /* Строка ниже создаётся по умолчанию. Это то, благодаря чему работает переворот дисплея
        Модель создаётся первый раз или возвращается, если уже была создана
         - благодаря ViewModelProvider. Навроде синглтона. */
        viewModel = ViewModelProvider(this)[MainViewModel::class.java] // ::class.java — не объект класса, а ссылка на тип класса
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) }) // Подписывается на наблюдение LiveData (и возвращает ссылку на неё), выполняя renderData()
        viewModel.getWeather()

        /*binding.name.text = viewModel.name
        Тоже самое. Откуда в viewModel name и userClicked?
        binding.button.setOnClickListener { viewModel.userClicked() }*/
    }

    private fun renderData(appState: AppState) = with(binding ) {
        when (appState) {
            is AppState.Success -> {
                    val weatherData = appState.weatherData
                    loadingLayout.visibility = View.GONE
                    Snackbar.make(mainView, "Success", Snackbar.LENGTH_LONG).show()
                    setData(weatherData)
                }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                    loadingLayout.visibility = View.GONE
                    //val error = appState.error // можно также взять error, но его ещё не положили в AppState
                    Snackbar.make(mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Reload") { viewModel.getWeather() }
                            .show()
            }
        }
    }

    private fun setData(weatherData: Weather) = with(binding) {
        cityName.text = weatherData.city.city
        cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                weatherData.city.lat.toString(),
                weatherData.city.lon.toString()
        )
        temperatureValue.text = weatherData.temperature.toString()
        feelsLikeValue.text = weatherData.feelslLike.toString()
    }
*/
}