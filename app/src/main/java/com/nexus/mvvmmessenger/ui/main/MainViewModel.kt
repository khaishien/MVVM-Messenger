package com.nexus.mvvmmessenger.ui.main

import android.app.Application
import com.nexus.mvvmmessenger.core.BaseViewModel
import com.nexus.mvvmmessenger.data.GetMessageCallback
import com.nexus.mvvmmessenger.model.MessageModel
import com.nexus.mvvmmessenger.utils.InjectorUtils
import com.nexus.mvvmmessenger.utils.SingleLiveEvent

class MainViewModel(application: Application) : BaseViewModel(application) {

    var getMessagesEvent = SingleLiveEvent<List<MessageModel>>()

    fun getMessages() {
        InjectorUtils.getMessageRepository(getApplication())
            .getMessages(object : GetMessageCallback {
                override fun onMessageLoad(messages: List<MessageModel>) {
                    getMessagesEvent.value = messages
                }

                override fun onError(error: String) {
                    showErrorEvent.value = error
                }

                override fun onEmpty() {
                    getMessagesEvent.value = listOf()
                }

            })
    }


}