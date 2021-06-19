package com.erdemer.e_ticaret

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.erdemer.e_ticaret.extension.invisible
import com.erdemer.e_ticaret.util.Constants
import com.erdemer.e_ticaret.view.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("ID", fragmentContainerView.id.toString())
        supportFragmentManager.findFragmentById(R.id.nav_graph)

        ivPinkCircle.invisible()

    }




    }


