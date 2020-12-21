package com.adityaprakash.covidinfo.ui.singleCountry


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
import com.adityaprakash.covidinfo.data.vo.SingleCountryData
import com.adityaprakash.covidinfo.data.vo.WorldData
import com.adityaprakash.covidinfo.databinding.FragmentSingleContinentBinding
import com.adityaprakash.covidinfo.databinding.FragmentSingleCountryBinding
import com.adityaprakash.covidinfo.ui.singleContinent.SingleContinentDataRepository
import com.adityaprakash.covidinfo.ui.singleContinent.SingleContinentViewModel


class SingleCountryFragment : Fragment() {

    private lateinit var binding:FragmentSingleCountryBinding
    private lateinit var viewModel: SingleCountryViewModel
    private var country:String? = ""
    private lateinit var singleCountryDataRepository: SingleCountryDataRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_single_country, container, false)

        country = requireArguments().getString("country_name")
        (requireActivity() as AppCompatActivity).supportActionBar?.title = country
        val apiService : CovidApiInterface = CovidApiObject.getClient()
        singleCountryDataRepository = SingleCountryDataRepository(apiService)

        viewModel = getViewModel()




        viewModel.singleCountryData.observe(viewLifecycleOwner, Observer {
            bindUI(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            binding.errorText.text = if (it == NetworkState.LOADING) "Loading" else ""
            binding.errorText.text = if (it == NetworkState.ERROR) "Couldn't fetched the data" else ""

        })

        return binding.root
    }

    fun bindUI( it: SingleCountryData){

        binding.continentName.text = it.country+" Covid Data"
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


    private fun getViewModel(): SingleCountryViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleCountryViewModel(singleCountryDataRepository, country.toString()) as T
            }
        })[SingleCountryViewModel::class.java]
    }
}