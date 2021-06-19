package com.erdemer.e_ticaret.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.erdemer.e_ticaret.R
import kotlinx.android.synthetic.main.bottom_sheet.*

/**
 * Created by alpkaraosmanoglu on 6/14/21
 */

class MyCustomDialog(private val onClickInterface: OnClickInterface) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        return inflater.inflate(R.layout.bottom_sheet, container, false)

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        dialog!!.btnBackToMainPage.setOnClickListener {
            onClickInterface.onClick(dialog!!)
        }

    }
}

interface OnClickInterface {
    fun onClick(dialog: Dialog)

}