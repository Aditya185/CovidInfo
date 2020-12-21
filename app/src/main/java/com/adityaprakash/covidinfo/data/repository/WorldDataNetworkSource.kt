package com.adityaprakash.covidinfo.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adityaprakash.covidinfo.data.api.CovidApiInterface
import com.adityaprakash.covidinfo.data.vo.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WorldDataNetworkSource(private val apiService: CovidApiInterface, private  val compositeDisposable: CompositeDisposable) {
    private val _networkState  = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState                   //with this get, no need to implement get function to get networkSate

    private val _downloadedWorldDetailsResponse =  MutableLiveData<WorldData>()
    val downloadedWorldResponse: LiveData<WorldData>
        get() = _downloadedWorldDetailsResponse

    private val _downloadedContinentDetailsResponse =  MutableLiveData<List<ContinentData>>()
    val downloadedContinentResponse: LiveData<List<ContinentData>>
        get() = _downloadedContinentDetailsResponse

    private val _downloadedSingleContinentDetailsResponse =  MutableLiveData<SingleContinentData>()
    val downloadedSingleContinentResponse: LiveData<SingleContinentData>
        get() = _downloadedSingleContinentDetailsResponse

    private val _downloadedCountryDetailsResponse =  MutableLiveData<List<CountryData>>()
    val downloadedCountryDetailsResponse:  LiveData<List<CountryData>>
        get() = _downloadedCountryDetailsResponse

    private val _downloadedSingleCountryDetailsResponse =  MutableLiveData<SingleCountryData>()
    val downloadedSingleCountryResponse: LiveData<SingleCountryData>
        get() = _downloadedSingleCountryDetailsResponse

    fun fetchWorldDetails() {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getWorldDetails()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedWorldDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("WorldDetailsDataSource", it.message)
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("WorldDetailsDataSource",e.message)
        }


    }

    fun fetchContinentDetails() {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getContinentDetails()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedContinentDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("WorldDetailsDataSource", it.message)
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("WorldDetailsDataSource",e.message)
        }


    }

    fun fetchSingleContinentDetails(continent: String) {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getSingleContinentDetails(continent)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedSingleContinentDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("WorldDetailsDataSource", it.message)
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("WorldDetailsDataSource",e.message)
        }


    }


    fun fetchCountryDetails() {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getCountryDetails()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedCountryDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("WorldDetailsDataSource", it.message)
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("WorldDetailsDataSource",e.message)
        }


    }

    fun fetchSingleCountryDetails(country: String) {

        _networkState.postValue(NetworkState.LOADING)


        try {
            compositeDisposable.add(
                apiService.getSingleCountryDetails(country)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedSingleCountryDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("WorldDetailsDataSource", it.message)
                        }
                    )
            )

        }

        catch (e: Exception){
            Log.e("WorldDetailsDataSource",e.message)
        }


    }

}