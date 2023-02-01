package com.example.androidAssignment3

import android.os.Bundle
import com.example.androidAssignment3.architecture.BaseActivity
import com.example.androidAssignment3.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

//    lateinit var navControler: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        navControler=Navigation.findNavController(this,R.id.nav_host_fragment)
//        MAIN = this
    }
}