package com.adityaprakash.covidinfo.data.vo

data class SingleContinentData(
    val active: String,
    val cases: String,
    val casesPerOneMillion: String,
    val continent: String,
    val critical: String,
    val deaths: String,
    val deathsPerOneMillion: String,
    val recovered: String,
    val tests: String,
    val testsPerOneMillion: String,
    val todayCases: String,
    val todayDeaths: String,
    val todayRecovered: String,

)