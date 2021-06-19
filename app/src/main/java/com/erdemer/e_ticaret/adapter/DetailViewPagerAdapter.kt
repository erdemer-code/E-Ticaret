package com.erdemer.e_ticaret.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erdemer.e_ticaret.R
import com.erdemer.e_ticaret.util.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_pager.view.*
import kotlinx.android.synthetic.main.product_detail_fragment.view.*
import java.lang.Exception

class DetailViewPagerAdapter(private val images: List<String>):
RecyclerView.Adapter<DetailViewPagerAdapter.DetailViewPagerViewHolder>(){

    inner class DetailViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager,parent,false)
        return DetailViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewPagerViewHolder, position: Int) {
        val currentImg = images[position]
        Picasso.get().load(currentImg).into(holder.itemView.ivViewPager,object: Callback{
            override fun onSuccess() {
                Log.i(Constants.DETAIL_VIEW_PAGE_ADAPTER,"Successfully fetched images")
            }

            override fun onError(e: Exception?) {
                Log.e(Constants.DETAIL_VIEW_PAGE_ADAPTER,"Error on fetching")
            }

        } )
    }

    override fun getItemCount(): Int = images.size
}
