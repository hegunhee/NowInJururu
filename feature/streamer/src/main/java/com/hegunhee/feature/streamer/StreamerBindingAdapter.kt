package com.hegunhee.feature.streamer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("setImage")
fun ImageView.setImage(url : String){
    Picasso
        .get()
        .load(url)
        .into(this)
}