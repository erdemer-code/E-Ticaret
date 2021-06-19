package com.erdemer.e_ticaret.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.erdemer.e_ticaret.extension.invisible
import com.erdemer.e_ticaret.extension.visible
import kotlinx.android.synthetic.main.activity_main.*

 class SharedPreferenceManager {

     var mPref: SharedPreferences? = null



      fun createAddSharedPreferences(context: Context,boolValue: Boolean) {
          mPref = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
          val myEdit = mPref!!.edit()
          myEdit.putBoolean(Constants.IS_ADDED_TAG,boolValue)
          myEdit.commit()
     }

    fun readFromSharedPreferences(context: Context, activity: Activity) {
        val sharedPreferences = context
            .getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val isAdded = sharedPreferences.getBoolean(Constants.IS_ADDED_TAG, false)
        if (isAdded) {
            activity?.ivPinkCircle?.visible()
        } else {
            activity?.ivPinkCircle?.invisible()
        }

    }
}