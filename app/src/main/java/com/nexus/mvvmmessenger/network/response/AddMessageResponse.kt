package com.nexus.mvvmmessenger.network.response

import com.google.gson.annotations.SerializedName
import com.nexus.mvvmmessenger.core.BaseResponse

class AddMessageResponse : BaseResponse() {

    @SerializedName("message")
    lateinit var message: String
}