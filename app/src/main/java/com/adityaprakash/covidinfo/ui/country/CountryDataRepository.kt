package com.adityaprakash.covidinfo.ui.country


import androidx.lifecycle.LiveData
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.repository.NetworkState
import com.adityaprakash.covidinfo.data.repository.WorldDataNetworkSource
import com.adityaprakash.covidinfo.data.vo.ContinentData
import com.adityaprakash.covidinfo.data.vo.CountryData
import io.reactivex.disposables.CompositeDisposable

class CountryDataRepository (private val apiService : CovidApiInterface) {
    lateinit var worldDataNetworkSource: WorldDataNetworkSource

    fun fetchCountryDetails (compositeDisposable: CompositeDisposable) : LiveData<List<CountryData>> {

        worldDataNetworkSource = WorldDataNetworkSource(apiService,compositeDisposable)
        worldDataNetworkSource.fetchCountryDetails()

        return worldDataNetworkSource.downloadedCountryDetailsResponse

    }

    fun getContinentDetailsNetworkState(): LiveData<NetworkState> {
        return worldDataNetworkSource.networkState
    }
}