package com.nexus.mvvmmessenger.ui.main

import com.nexus.mvvmmessenger.R
import com.nexus.mvvmmessenger.core.BaseActivity
import com.nexus.mvvmmessenger.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java
    override val layout: Int
        get() = R.layout.activity_main

    override fun onFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.content,
                MainFragment()
            )
            .commitNow()
    }

    override fun onInit() {

    }

}
