package com.erdemer.e_ticaret.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.erdemer.e_ticaret.R
import com.erdemer.e_ticaret.enum.CategoryType
import com.erdemer.e_ticaret.extension.changeTextColor
import com.erdemer.e_ticaret.extension.gone
import com.erdemer.e_ticaret.extension.makeStrike
import com.erdemer.e_ticaret.model.Product
import com.erdemer.e_ticaret.util.Constants
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*
import java.lang.Exception

class ProductRecyclerAdapter(private val productList: List<Product>, var onProductItemClickListener: OnProductItemClickListener ): RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(){
            val item = productList[adapterPosition]
            Picasso.get().load(item.image).into(itemView.ivProductImage, object : Callback{
                override fun onSuccess() {
                    Log.i(Constants.PRODUCT_RECYCLER_VIEW_TAG, "Successfully images fetched")
                }

                override fun onError(e: Exception?) {
                   Log.e(Constants.PRODUCT_RECYCLER_VIEW_TAG, "Error on image fetching")
                }
            })
            itemView.tvProductName.text = item.productName
            item.priceDiscount?.let {
                itemView.tvOldPrice.text = item.price + "₺"
                itemView.tvNewPrice.text = item.priceDiscount + "₺"
                itemView.tvOldPrice.makeStrike()
            }?: run {
                itemView.tvOldPrice.text = item.price + "₺"
                itemView.tvNewPrice.gone()
                itemView.tvOldPrice.changeTextColor(R.color.title_color)
            }
            item.category?.let { checkCategory(it,itemView) }
            itemView.setOnClickListener {
                onProductItemClickListener.onClick(adapterPosition)
            }
        }
    }

}

fun checkCategory(data:String, itemView: View){
    when(data){
        CategoryType.MALE.value-> {
            itemView.tvCategoryName.setBackgroundResource(R.drawable.male_circle)
            itemView.tvCategoryName.changeTextColor(R.color.male_blue)
        }
        CategoryType.FEMALE.value -> {
            itemView.tvCategoryName.setBackgroundResource(R.drawable.female_circle)
            itemView.tvCategoryName.changeTextColor(R.color.female_text)
        }
    }
    itemView.tvCategoryName.text = data

}

interface OnProductItemClickListener{
    fun onClick(position: Int)
}