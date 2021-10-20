package com.example.bonusassignments.SPR

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("custom-people/")
    fun getData(): Call<List<MyDataItem>>

    @POST("custom-people/")
    fun addName(@Body person: MyDataItem): Call<MyDataItem>
}