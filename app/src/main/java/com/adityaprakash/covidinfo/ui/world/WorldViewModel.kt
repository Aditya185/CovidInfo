package com.adityaprakash.covidinfo.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.vo.WorldData
import com.adityaprakash.covidinfo.ui.world.WorldDataRepository
import io.reactivex.disposables.CompositeDisposable

class WorldViewModel(private val worldDataRepository: WorldDataRepository)  : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  worldData : LiveData<WorldData> by lazy {
        worldDataRepository.fetchSingleWorldDetails(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        worldDataRepository.getWorldDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}