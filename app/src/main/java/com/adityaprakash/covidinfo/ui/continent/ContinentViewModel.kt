package com.adityaprakash.covidinfo.ui.continent


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.data.vo.WorldData
import com.adityaprakash.covidinfo.ui.world.WorldDataRepository
import io.reactivex.disposables.CompositeDisposable

class ContinentViewModel(private val continentDataRepository: ContinentDataRepository)  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  continentData : LiveData<List<ContinentData>> by lazy {
        continentDataRepository.fetchSingleContinentDetails(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        continentDataRepository.getContinentDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}