package com.androidAssignment3.ui

import android.os.Bundle
import com.androidAssignment3.architecture.BaseActivity
import com.androidAssignment3.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}