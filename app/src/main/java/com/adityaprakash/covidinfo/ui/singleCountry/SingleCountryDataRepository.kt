package com.adityaprakash.covidinfo.ui.singleCountry


import androidx.lifecycle.LiveData
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.repository.WorldDataNetworkSource
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.data.vo.SingleContinentData
import com.adityaprakash.covidinfo.data.vo.SingleCountryData
import com.adityaprakash.covidinfo.data.vo.WorldData
import io.reactivex.disposables.CompositeDisposable

class SingleCountryDataRepository (private val apiService : CovidApiInterface) {
    lateinit var worldDataNetworkSource: WorldDataNetworkSource

    fun fetchSingleCountryDetails (compositeDisposable: CompositeDisposable,country:String) : LiveData<SingleCountryData> {

        worldDataNetworkSource = WorldDataNetworkSource(apiService,compositeDisposable)
        worldDataNetworkSource.fetchSingleCountryDetails(country)

        return worldDataNetworkSource.downloadedSingleCountryResponse

    }

    fun getSingleContinentDetailsNetworkState(): LiveData<NetworkState> {
        return worldDataNetworkSource.networkState
    }
}