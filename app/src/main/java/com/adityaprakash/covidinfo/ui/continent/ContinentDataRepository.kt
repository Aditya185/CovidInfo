package com.adityaprakash.covidinfo.ui.continent

import androidx.lifecycle.LiveData
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.repository.WorldDataNetworkSource
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.data.vo.WorldData
import io.reactivex.disposables.CompositeDisposable

class ContinentDataRepository (private val apiService : CovidApiInterface) {
    lateinit var worldDataNetworkSource: WorldDataNetworkSource

    fun fetchSingleContinentDetails (compositeDisposable: CompositeDisposable) : LiveData<List<ContinentData>> {

        worldDataNetworkSource = WorldDataNetworkSource(apiService,compositeDisposable)
        worldDataNetworkSource.fetchContinentDetails()

        return worldDataNetworkSource.downloadedContinentResponse

    }

    fun getContinentDetailsNetworkState(): LiveData<NetworkState> {
        return worldDataNetworkSource.networkState
    }
}