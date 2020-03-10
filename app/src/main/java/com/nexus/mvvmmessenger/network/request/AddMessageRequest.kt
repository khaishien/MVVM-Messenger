package com.nexus.mvvmmessenger.network.request

import com.google.gson.annotations.SerializedName
import com.nexus.mvvmmessenger.core.BaseRequest

class AddMessageRequest(
    @SerializedName("message") var message: String
) : BaseRequest()