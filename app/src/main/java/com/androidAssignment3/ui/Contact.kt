package com.androidAssignment3.ui

import java.io.Serializable

data class Contact(
    val imageURL: String,
    val name: String,
    val career: String,
    val eMail: String = "",
    val phone: String = "",
    val address: String = "",
    val birth: String = ""
) : Serializable
