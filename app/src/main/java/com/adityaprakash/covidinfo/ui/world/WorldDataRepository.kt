package com.adityaprakash.covidinfo.ui.world

import androidx.lifecycle.LiveData
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.repository.WorldDataNetworkSource
import com.adityaprakash.covidinfo.data.vo.WorldData
import io.reactivex.disposables.CompositeDisposable

class WorldDataRepository (private val apiService : CovidApiInterface) {
    lateinit var worldDataNetworkSource: WorldDataNetworkSource

    fun fetchSingleWorldDetails (compositeDisposable: CompositeDisposable) : LiveData<WorldData> {

        worldDataNetworkSource = WorldDataNetworkSource(apiService,compositeDisposable)
        worldDataNetworkSource.fetchWorldDetails()

        return worldDataNetworkSource.downloadedWorldResponse

    }

    fun getWorldDetailsNetworkState(): LiveData<NetworkState> {
        return worldDataNetworkSource.networkState
    }
}