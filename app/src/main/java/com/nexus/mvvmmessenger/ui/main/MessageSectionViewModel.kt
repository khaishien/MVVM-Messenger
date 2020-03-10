package com.nexus.mvvmmessenger.ui.main

import com.nexus.mvvmmessenger.model.SectionMessageModel
import com.nexus.mvvmmessenger.utils.DateUtils

class MessageSectionViewModel(sectionMessageModel: SectionMessageModel) {

    val time = DateUtils.formatDate(
        DateUtils.fullDateFormat,
        DateUtils.readableDateFormat,
        sectionMessageModel.timestamp
    )

}