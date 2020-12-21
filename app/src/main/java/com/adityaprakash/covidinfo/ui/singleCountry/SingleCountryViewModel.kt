package com.adityaprakash.covidinfo.ui.singleCountry


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.SingleContinentData
import com.adityaprakash.covidinfo.data.vo.SingleCountryData
import com.adityaprakash.covidinfo.data.vo.WorldData
import com.adityaprakash.covidinfo.ui.singleContinent.SingleContinentDataRepository
import com.adityaprakash.covidinfo.ui.world.WorldDataRepository
import io.reactivex.disposables.CompositeDisposable

class SingleCountryViewModel(private val singleCountryDataRepository: SingleCountryDataRepository,country:String)  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  singleCountryData : LiveData<SingleCountryData> by lazy {
        singleCountryDataRepository.fetchSingleCountryDetails(compositeDisposable,country)
    }

    val networkState : LiveData<NetworkState> by lazy {
        singleCountryDataRepository.getSingleContinentDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}