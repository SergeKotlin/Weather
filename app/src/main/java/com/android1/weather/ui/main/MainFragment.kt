package com.android1.weather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.android1.weather.R
import com.android1.weather.databinding.FragmentDetailsBinding
import com.android1.weather.databinding.MainFragmentBinding
import com.android1.weather.model.AppState
import com.android1.weather.model.entities.Weather
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    //private var binding: ResultProfileBinding? = null // эта переменная существует только между методами onCreateView и onDestroyView
    //private val binding get() = binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /*binding = null Обнуляем _binding в onDestroyView, чтобы избежать утечек и не желаемого поведения.
        (В Activity ничего похожего делать не требуется)*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /* Строка ниже создаётся по умолчанию. Это то, благодаря чему работает переворот дисплея
        Модель создаётся первый раз или возвращается, если уже была создана
         - благодаря ViewModelProvider. Навроде синглтона. */
        viewModel = ViewModelProvider(this)[MainViewModel::class.java] // ::class.java — не объект класса, а ссылка на тип класса
        //val observer = Observer<Any> { renderData(it) }
        //viewModel.getData().observe(viewLifecycleOwner, observer) // Возвращает ссылку на LiveData и подписывается на наблюдение
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
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
                    //Snackbar.make(mainView, "Success", Snackbar.LENGTH_LONG).show()
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
//        Toast.makeText(context, "data", Toast.LENGTH_LONG).show()
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

}