package com.nexus.mvvmmessenger.data

import androidx.room.TypeConverter
import com.nexus.mvvmmessenger.constant.MessageDirection

class Converters {


    @TypeConverter
    fun fromDirection(value: MessageDirection): String {
        return value.value
    }

    @TypeConverter
    fun toDirection(value: String): MessageDirection {
        if (value == "OUTGOING") {
            return MessageDirection.OUTGOING
        }
        return MessageDirection.INCOMING
    }
}