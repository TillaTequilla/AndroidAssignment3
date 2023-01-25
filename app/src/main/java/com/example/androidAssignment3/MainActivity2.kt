package com.example.androidAssignment3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidAssignment3.constance.MAIN
import com.example.androidAssignment3.databinding.ActivityMain2Binding

class MainActivity2 : BaseActivity<ActivityMain2Binding>(ActivityMain2Binding::inflate) {

    lateinit var navControler: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navControler=Navigation.findNavController(this,R.id.nav_host_fragment)
        MAIN = this
    }
}