package com.nexus.mvvmmessenger.constant

import androidx.room.TypeConverter


enum class MessageDirection(val value: String) {
    OUTGOING("OUTGOING"),
    INCOMING("INCOMING")
}