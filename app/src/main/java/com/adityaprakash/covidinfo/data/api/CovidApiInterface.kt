package com.adityaprakash.covidinfo.data.api

import com.adityaprakash.covidinfo.data.vo.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApiInterface {
    @GET("v3/covid-19/all")
    fun getWorldDetails(): Single<WorldData>

    @GET("v3/covid-19/continents")
    fun getContinentDetails(): Single<List<ContinentData>>

    @GET("v3/covid-19/countries")
    fun getCountryDetails(): Single<List<CountryData>>

    @GET("v3/covid-19/continents/{continent}")
    fun getSingleContinentDetails(@Path("continent") continent:String): Single<SingleContinentData>

    @GET("v3/covid-19/countries/{country}")
    fun getSingleCountryDetails(@Path("country") country:String): Single<SingleCountryData>
}