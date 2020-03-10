package com.nexus.mvvmmessenger.ui.main

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.nexus.mvvmmessenger.constant.MessageDirection
import com.nexus.mvvmmessenger.core.BaseViewModel
import com.nexus.mvvmmessenger.data.GetMessageCallback
import com.nexus.mvvmmessenger.model.MessageModel
import com.nexus.mvvmmessenger.network.ApiClient
import com.nexus.mvvmmessenger.network.request.AddMessageRequest
import com.nexus.mvvmmessenger.network.response.AddMessageResponse
import com.nexus.mvvmmessenger.utils.InjectorUtils
import com.nexus.mvvmmessenger.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : BaseViewModel(application) {

    var getMessagesEvent = SingleLiveEvent<List<MessageModel>>()
    var onAddMessageEvent = SingleLiveEvent<Boolean>()
    var isSending = ObservableBoolean(false)


    private var onGetMessageCallback = object : GetMessageCallback {
        override fun onMessageLoad(messages: List<MessageModel>) {
            getMessagesEvent.value = messages
        }

        override fun onError(error: String) {
            showErrorEvent.value = error
        }

        override fun onEmpty() {
            getMessagesEvent.value = listOf()
        }
    }

    fun getMessages() {
        InjectorUtils.getMessageRepository(getApplication())
            .getMessages(onGetMessageCallback)
    }

    fun deleteMessage(messageModel: MessageModel) {
        InjectorUtils.getMessageRepository(getApplication())
            .deleteMessage(messageModel, onGetMessageCallback)
    }

    fun addMessage(newMessage: String) {
        if (newMessage.isEmpty())
            return
        isSending.set(true)
        val request = AddMessageRequest(newMessage)
        ApiClient.service.addMessage(request)
            .enqueue(object : Callback<AddMessageResponse> {
                override fun onFailure(call: Call<AddMessageResponse>, t: Throwable) {
                    isSending.set(false)
                    onAddMessageEvent.value = false
                    showErrorEvent.value = t.message
                }

                override fun onResponse(
                    call: Call<AddMessageResponse>,
                    response: Response<AddMessageResponse>
                ) {
                    isSending.set(false)
                    if (response.isSuccessful) {
                        val body = checkNotNull(response.body())
                        val messageModel = MessageModel(
                            body.createdAt,
                            MessageDirection.OUTGOING.value,
                            body.message
                        )
                        InjectorUtils.getMessageRepository(getApplication())
                            .addMessage(messageModel, onGetMessageCallback)
                        onAddMessageEvent.value = true
                    } else {
                        onAddMessageEvent.value = false
                        showErrorEvent.value = "Failed to add message, try again later"
                    }
                }

            })
    }


}