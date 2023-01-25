//package com.example.androidAssignment3
//
//import android.content.Intent
//import android.os.Bundle
//import com.example.androidAssignment3.constance.Constance
//
//
//class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val name: String? = intent.getStringExtra(Constance.INTENT_NAME)
//        binding.tvName.text = name
//
//        binding.bContacts.setOnClickListener {
//            val intent = Intent(this@MainActivity, ContactsActivity::class.java)
//            startActivity(intent)
//        }
//    }
//}