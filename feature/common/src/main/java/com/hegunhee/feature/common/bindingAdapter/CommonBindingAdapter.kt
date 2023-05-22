package com.hegunhee.feature.common.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("setImage")
fun ImageView.setImage(url : String){
    if(url.isNotEmpty() && this.width != 0 && this.height != 0){
        Picasso
            .get()
            .load(url)
            .resize(this.width,this.height)
            .into(this)
    }

}