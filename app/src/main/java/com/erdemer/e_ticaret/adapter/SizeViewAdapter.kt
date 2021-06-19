package com.erdemer.e_ticaret.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.erdemer.e_ticaret.R
import com.erdemer.e_ticaret.model.SizeModel
import kotlinx.android.synthetic.main.size_item.*
import kotlinx.android.synthetic.main.size_item.view.*

class SizeViewAdapter(private val context: Context, private var sizeList: List<SizeModel>, private val onSizeItemClickListener:OnSizeItemClickListener): RecyclerView.Adapter<SizeViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(){
            val item = sizeList[adapterPosition]
            var flag = false
            itemView.size_button.text = item.sizeText
            itemView.setOnClickListener {
                onSizeItemClickListener.onClick(adapterPosition)
            }
            if (item.isSelected){
                itemView.size_button.background = ContextCompat.getDrawable(context, R.drawable.size_button_selected)
            } else {
                itemView.size_button.background = ContextCompat.getDrawable(context, R.drawable.size_button_normal)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.size_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = sizeList.size

    public fun setSizeList(sizeList_: List<SizeModel>){
        this.sizeList = sizeList_
        notifyDataSetChanged()
    }

}



interface OnSizeItemClickListener{
    fun onClick(position:Int)
}