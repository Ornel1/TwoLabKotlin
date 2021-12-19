package com.example.twolabkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("/data/2.5/weather?")
    fun getSityWeather(
        @Query("q") sity: String?,
        @Query("appid") app_id: String?
    ): Call<ResponseWe3?>?


}