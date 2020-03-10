package com.nexus.mvvmmessenger.data

import android.os.Handler
import android.os.Looper
import com.nexus.mvvmmessenger.model.MessageModel
import java.util.concurrent.Executors


class MessageRepository private constructor(private val messageDao: MessageDao) {

    fun getMessages(callback: GetMessageCallback) {
        Executors.newSingleThreadExecutor().execute {
            val messages = messageDao.getMessages()
            val mainThreadHandler = Handler(Looper.getMainLooper())
            mainThreadHandler.post {
                if (messages.isEmpty()) {
                    callback.onEmpty()
                } else {
                    callback.onMessageLoad(messages)

                }
            }
        }
    }

    fun addMessage(messageModel: MessageModel) = messageDao.addMessage(messageModel)

    fun deleteMessage(messageModel: MessageModel) = messageDao.deleteMessage(messageModel)


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: MessageRepository? = null

        fun getInstance(messageDao: MessageDao) =
            instance ?: synchronized(this) {
                instance ?: MessageRepository(messageDao).also { instance = it }
            }
    }
}
