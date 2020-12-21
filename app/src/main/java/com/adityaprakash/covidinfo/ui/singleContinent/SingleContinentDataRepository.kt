package com.adityaprakash.covidinfo.ui.singleContinent

import androidx.lifecycle.LiveData
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.repository.WorldDataNetworkSource
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.data.vo.SingleContinentData
import com.adityaprakash.covidinfo.data.vo.WorldData
import io.reactivex.disposables.CompositeDisposable

class SingleContinentDataRepository (private val apiService : CovidApiInterface) {
    lateinit var worldDataNetworkSource: WorldDataNetworkSource

    fun fetchSingleContinentDetails (compositeDisposable: CompositeDisposable,continent:String) : LiveData<SingleContinentData> {

        worldDataNetworkSource = WorldDataNetworkSource(apiService,compositeDisposable)
        worldDataNetworkSource.fetchSingleContinentDetails(continent)

        return worldDataNetworkSource.downloadedSingleContinentResponse

    }

    fun getSingleContinentDetailsNetworkState(): LiveData<NetworkState> {
        return worldDataNetworkSource.networkState
    }
}