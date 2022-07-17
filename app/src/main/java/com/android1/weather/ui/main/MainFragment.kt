package com.android1.weather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android1.weather.R
import com.android1.weather.databinding.FragmentMainBinding
import com.android1.weather.ui.viewModel.AppState
import com.android1.weather.ui.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private val adapter = MainFragmentAdapter()
    private var isDataSetWorld: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it) })
        viewModel.getWeatherFromLocalSourceRus()
    }

    private fun changeWeatherDataSet() {
        if (isDataSetWorld) {
            viewModel.getWeatherFromLocalSourceWorld()
//            binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceRus()
//            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        isDataSetWorld = !isDataSetWorld
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherData)
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainFragmentFAB, getString(R.string.error),
                        Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.reload)) {
                        viewModel.getWeatherFromLocalSourceRus() }
                    .show()
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}