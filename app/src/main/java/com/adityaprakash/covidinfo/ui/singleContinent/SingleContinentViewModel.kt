package com.adityaprakash.covidinfo.ui.singleContinent


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.SingleContinentData
import com.adityaprakash.covidinfo.data.vo.WorldData
import com.adityaprakash.covidinfo.ui.world.WorldDataRepository
import io.reactivex.disposables.CompositeDisposable

class SingleContinentViewModel(private val singleContinentDataRepository: SingleContinentDataRepository,continent:String)  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  singleContinentData : LiveData<SingleContinentData> by lazy {
        singleContinentDataRepository.fetchSingleContinentDetails(compositeDisposable,continent)
    }

    val networkState : LiveData<NetworkState> by lazy {
        singleContinentDataRepository.getSingleContinentDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}