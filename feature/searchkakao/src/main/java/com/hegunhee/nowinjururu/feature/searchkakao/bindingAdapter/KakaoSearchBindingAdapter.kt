package com.hegunhee.nowinjururu.feature.searchkakao.bindingAdapter

import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setHtmlText")
fun TextView.setHtmlText(html : String) {
    text = Html.fromHtml(html,FROM_HTML_MODE_LEGACY)
}