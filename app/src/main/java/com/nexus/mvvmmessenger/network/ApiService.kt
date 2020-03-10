package com.nexus.mvvmmessenger.network

import com.nexus.mvvmmessenger.core.BaseResponse
import com.nexus.mvvmmessenger.network.request.AddMessageRequest
import com.nexus.mvvmmessenger.network.response.AddMessageResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @POST("users")
    fun addMessage(@Body request: AddMessageRequest): Call<AddMessageResponse>


}