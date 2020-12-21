package com.adityaprakash.covidinfo.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.adityaprakash.covidinfo.R

import android.util.Log
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityaprakash.covidinfo.adapters.ContinentAdapter
import com.adityaprakash.covidinfo.adapters.CountryAdapter
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.api.CovidApiObject
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.databinding.FragmentContinentBinding
import com.adityaprakash.covidinfo.databinding.FragmentCountryBinding
import com.adityaprakash.covidinfo.ui.continent.ContinentDataRepository
import com.adityaprakash.covidinfo.ui.continent.ContinentViewModel


class CountryFragment : Fragment() {

    private lateinit var binding:FragmentCountryBinding
    private lateinit var viewModel: CountryViewModel
    private lateinit var countryDataRepository: CountryDataRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country, container, false)



        val apiService : CovidApiInterface = CovidApiObject.getClient()
        countryDataRepository = CountryDataRepository(apiService)

        viewModel = getViewModel()

        binding.continentRecycler.layoutManager = LinearLayoutManager(context)


        viewModel.countryData.observe(viewLifecycleOwner, Observer {
            binding.continentRecycler.adapter = CountryAdapter(it)
            //bindUI(it)
        })
//        viewModel.networkState.observe(viewLifecycleOwner, Observer {
//            binding.textContinent.text = if (it == NetworkState.LOADING) "Loading" else ""
//            binding.textContinent.text = if (it == NetworkState.ERROR) "Couldn't fetched the data" else ""
//
//        })

        return binding.root
    }

//    fun bindUI( it: List<ContinentData>){
//        var str:String = ""
//        for(item in it){
//            str+=item.cases
//            str+=item.active
//            str+=item.continent
//        }
//        binding.textContinent.text =str
//        Log.d("hello",str)
//    }


    private fun getViewModel(): CountryViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return CountryViewModel(countryDataRepository) as T
            }
        })[CountryViewModel::class.java]
    }
}