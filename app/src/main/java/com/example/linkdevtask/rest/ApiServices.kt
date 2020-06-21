package com.example.linkdevtask.rest

import com.example.linkdevtask.model.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("articles")
    fun getResponseOneAsync(@Query("source") source:String, @Query("apiKey") apiKey:String) : Deferred<Response<ApiResponse>>
    @GET("articles")
    fun getResponseTwoAsync(@Query("source") source:String, @Query("apiKey") apiKey:String) : Deferred<Response<ApiResponse>>
}