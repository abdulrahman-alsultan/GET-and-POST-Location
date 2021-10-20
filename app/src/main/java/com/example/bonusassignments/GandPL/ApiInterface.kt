package com.example.bonusassignments.GandPL

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("test/")
    fun getData(): Call<List<MyDataItem>>

    @POST("test/")
    fun addName(@Body person: MyDataItem): Call<MyDataItem>
}