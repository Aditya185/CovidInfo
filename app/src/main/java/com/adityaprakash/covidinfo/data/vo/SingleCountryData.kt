package com.adityaprakash.covidinfo.data.vo

data class SingleCountryData(
    val active: String,
    val activePerOneMillion: String,
    val cases: String,
    val casesPerOneMillion: String,
    val continent: String,
    val country: String,

    val critical: String,

    val deaths: String,
    val deathsPerOneMillion: String,

    val population: String,
    val recovered: String,

    val tests: String,
    val testsPerOneMillion: String,
    val todayCases: String,
    val todayDeaths: String,
    val todayRecovered: String,
)