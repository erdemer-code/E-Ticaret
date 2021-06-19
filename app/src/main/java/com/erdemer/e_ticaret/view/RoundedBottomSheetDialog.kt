package com.erdemer.e_ticaret.view

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erdemer.e_ticaret.R
import com.erdemer.e_ticaret.util.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.view.*

class RoundedBottomSheetDialog(): BottomSheetDialogFragment() {

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            bottomSheetDialog.setCancelable(false)
        }
        return bottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet,container,false)

        view.btnBackToMainPage.setOnClickListener {
            val action = RoundedBottomSheetDialogDirections.actionRoundedBottomSheetDialogToHomeFragment2()
            findNavController().navigate(action)
            createSharedPreferences()
        }
        return view
    }

    fun createSharedPreferences(){
        val preferences = this.requireActivity().getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val myEdit = preferences.edit()
        myEdit.putBoolean(Constants.IS_ADDED_TAG,true)
        myEdit.commit()
    }

}
