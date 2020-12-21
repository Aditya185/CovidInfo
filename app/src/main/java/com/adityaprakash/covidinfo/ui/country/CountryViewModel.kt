package com.adityaprakash.covidinfo.ui.country


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.data.vo.CountryData
import com.adityaprakash.covidinfo.data.vo.WorldData
import com.adityaprakash.covidinfo.ui.continent.ContinentDataRepository
import com.adityaprakash.covidinfo.ui.world.WorldDataRepository
import io.reactivex.disposables.CompositeDisposable

class CountryViewModel(private val countryDataRepository: CountryDataRepository)  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  countryData : LiveData<List<CountryData>> by lazy {
        countryDataRepository.fetchCountryDetails(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        countryDataRepository.getContinentDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}