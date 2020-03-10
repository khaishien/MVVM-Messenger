package com.nexus.mvvmmessenger.utils

import android.content.Context
import com.nexus.mvvmmessenger.data.AppDatabase
import com.nexus.mvvmmessenger.data.MessageRepository

object InjectorUtils {

     fun getMessageRepository(context: Context): MessageRepository {
        return MessageRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).messageDao()
        )
    }
}