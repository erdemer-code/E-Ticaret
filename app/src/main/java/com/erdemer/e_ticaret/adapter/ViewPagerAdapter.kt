package com.erdemer.e_ticaret.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erdemer.e_ticaret.R
import com.erdemer.e_ticaret.model.Category
import com.erdemer.e_ticaret.util.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_pager.view.*
import java.lang.Exception

class ViewPagerAdapter(val images: List<Category>):
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImg = images[position].imageUrl
        Picasso.get().load(currentImg).into(holder.itemView.ivViewPager, object :Callback {
            override fun onSuccess() {
                Log.i(Constants.VIEW_PAGE_TAG,"Successfully fetched images")
            }

            override fun onError(e: Exception?) {
                Log.e(Constants.VIEW_PAGE_TAG,"Error on fetching")
            }

        })
    }

    override fun getItemCount(): Int = images.size

    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}