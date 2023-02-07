package com.androidAssignment3.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.androidAssignment3.R
import com.androidAssignment3.architecture.BaseActivity
import com.androidAssignment3.databinding.ActivityMainBinding
import com.androidAssignment3.ui.fragments.AuthActivityViewModel
import com.androidAssignment3.ui.fragments.ProfileFragment
import com.androidAssignment3.util.Constance

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = intent.getStringExtra(Constance.INTENT_NAME)!!

    }

    fun getData() : String{
        return name
    }
}