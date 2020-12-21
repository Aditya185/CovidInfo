package com.adityaprakash.covidinfo.ui.singleContinent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.adityaprakash.covidinfo.R
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.api.CovidApiObject
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.SingleContinentData
import com.adityaprakash.covidinfo.data.vo.WorldData
import com.adityaprakash.covidinfo.databinding.FragmentSingleContinentBinding


class SingleContinentFragment : Fragment() {

    private lateinit var binding:FragmentSingleContinentBinding
    private lateinit var viewModel: SingleContinentViewModel
    private var continent:String? = ""
    private lateinit var singleContinentDataRepository: SingleContinentDataRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_single_continent, container, false)

        continent = requireArguments().getString("continent_name")
        (requireActivity() as AppCompatActivity).supportActionBar?.title = continent
        val apiService : CovidApiInterface = CovidApiObject.getClient()
        singleContinentDataRepository = SingleContinentDataRepository(apiService)

        viewModel = getViewModel()




        viewModel.singleContinentData.observe(viewLifecycleOwner, Observer {
            bindUI(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            binding.errorText.text = if (it == NetworkState.LOADING) "Loading" else ""
            binding.errorText.text = if (it == NetworkState.ERROR) "Couldn't fetched the data" else ""

        })

        return binding.root
    }

    fun bindUI( it: SingleContinentData){

        binding.continentName.text = it.continent+" Covid Data"
        binding.totalCases.text = "Total Cases: "+ it.cases
        binding.totalActive.text = "Total Active Cases: "+it.active
        binding.totalCritical.text = "Total Critical Cases: "+it.critical
        binding.totalDeath.text = "Total Deaths: "+it.deaths
        binding.totalRecovered.text = "Total Recovery: "+it.recovered
        binding.totalTests.text = "Total Tests: "+it.tests
        binding.todayCases.text = "Today Cases: "+it.todayCases
        binding.todayDeath.text = "Today Deaths: "+it.todayDeaths
        binding. todayRecovered.text = "Today Recovery: "+it.todayRecovered
        binding.casePerOneMillion.text = "Cases per One Million: "+it.casesPerOneMillion
        binding.deathPerOneMillion.text = "Deaths per One Million: "+it.deathsPerOneMillion
        binding.testPerOneMillion.text = "Tests per One Million: "+it.testsPerOneMillion


    }


    private fun getViewModel(): SingleContinentViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleContinentViewModel(singleContinentDataRepository, continent.toString()) as T
            }
        })[SingleContinentViewModel::class.java]
    }
}