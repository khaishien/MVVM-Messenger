package com.nexus.mvvmmessenger.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nexus.mvvmmessenger.utils.SingleLiveEvent


open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var showErrorEvent: SingleLiveEvent<String> = SingleLiveEvent()

}