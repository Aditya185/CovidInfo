package com.adityaprakash.covidinfo.data.vo

data class WorldData(
    val active: String,
    val affectedCountries: String,
    val cases: String,
    val casesPerOneMillion: String,
    val critical: String,
    val deaths: String,
    val deathsPerOneMillion: String,
    val recovered: String,
    val tests: String,
    val todayCases: String,
    val todayDeaths: String,
    val todayRecovered: String,
    val testsPerOneMillion: String
)