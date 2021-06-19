package com.erdemer.e_ticaret.extension

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.widget.TextView
import com.erdemer.e_ticaret.util.Constants
import kotlinx.android.synthetic.main.item_product.view.*


fun TextView.makeStrike(){
        this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}
fun TextView.changeColor(color: String) {
    this.setTextColor(Color.parseColor(color))
}

fun TextView.convertHtml(html:String){
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
}

