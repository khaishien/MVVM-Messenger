package com.nexus.mvvmmessenger.data

import androidx.room.*
import com.nexus.mvvmmessenger.model.MessageModel

@Dao
interface MessageDao {

    @Query("SELECT * FROM message")
    fun getMessages(): List<MessageModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMessage(messageModel: MessageModel)

    @Insert
    fun addMessages(messageModels: List<MessageModel>)

    @Delete
    fun deleteMessage(messageModel: MessageModel)
}