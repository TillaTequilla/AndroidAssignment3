package com.androidAssignment3.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.addCircleImage(photo: String?) {
    Glide.with(this.context)
        .load(photo)
        .circleCrop()
        .into(this)
}