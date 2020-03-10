package com.nexus.mvvmmessenger.data

import com.nexus.mvvmmessenger.model.MessageModel

interface GetMessageCallback {

    fun onMessageLoad(messages: List<MessageModel>)
    fun onError(error: String)
    fun onEmpty()
}