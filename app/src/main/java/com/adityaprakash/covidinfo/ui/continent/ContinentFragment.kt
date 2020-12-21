package com.adityaprakash.covidinfo.ui.continent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityaprakash.covidinfo.R
import com.adityaprakash.covidinfo.adapters.ContinentAdapter
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.api.CovidApiObject
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.databinding.FragmentContinentBinding


class ContinentFragment : Fragment() {

    private lateinit var binding:FragmentContinentBinding
    private lateinit var viewModel: ContinentViewModel
    private lateinit var continentDataRepository: ContinentDataRepository
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_continent, container, false)



        val apiService : CovidApiInterface = CovidApiObject.getClient()
        continentDataRepository = ContinentDataRepository(apiService)

        viewModel = getViewModel()

        binding.continentRecycler.layoutManager = LinearLayoutManager(context)


        viewModel.continentData.observe(viewLifecycleOwner, Observer {
            binding.continentRecycler.adapter = ContinentAdapter(it)
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


    private fun getViewModel(): ContinentViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ContinentViewModel(continentDataRepository) as T
            }
        })[ContinentViewModel::class.java]
    }
}