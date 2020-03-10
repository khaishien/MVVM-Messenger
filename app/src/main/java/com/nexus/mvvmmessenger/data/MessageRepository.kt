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

    fun addMessage(messageModel: MessageModel, callback: GetMessageCallback) {
        Executors.newSingleThreadExecutor().execute {
            messageDao.addMessage(messageModel)
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

    fun deleteMessage(messageModel: MessageModel, callback: GetMessageCallback) {
        Executors.newSingleThreadExecutor().execute {
            messageDao.deleteMessage(messageModel)
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
