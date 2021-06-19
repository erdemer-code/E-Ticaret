package com.erdemer.e_ticaret.extension

import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.erdemer.e_ticaret.R
import com.erdemer.e_ticaret.util.Constants
import kotlinx.android.synthetic.main.item_product.view.*


fun TextView.makeStrike(){
        this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}


fun TextView.changeTextColor(colorId:Int){
    this.setTextColor(resources.getColor(colorId,context.theme));
}

fun TextView.convertHtml(html:String){
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(html)
    }
}

