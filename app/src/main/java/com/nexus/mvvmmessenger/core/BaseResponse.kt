package com.nexus.mvvmmessenger.core

import com.google.gson.annotations.SerializedName


open class BaseResponse {

    @SerializedName("id")
    lateinit var id: String


    @SerializedName("createdAt")
    lateinit var createdAt: String
}