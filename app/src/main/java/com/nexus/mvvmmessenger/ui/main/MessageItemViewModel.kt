package com.nexus.mvvmmessenger.ui.main

import com.nexus.mvvmmessenger.model.MessageModel
import com.nexus.mvvmmessenger.utils.DateUtils
import java.util.*

class MessageItemViewModel(val messageModel: MessageModel) {

    val message = messageModel.message
    val time = DateUtils.formatDate(
        DateUtils.fullDateFormat,
        DateUtils.readableTimeFormat,
        messageModel.timestamp
    )

}