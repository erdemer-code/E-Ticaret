package com.erdemer.e_ticaret.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erdemer.e_ticaret.model.ProductDetailResponse
import com.erdemer.e_ticaret.model.SizeModel
import com.erdemer.e_ticaret.network.NetworkHelper
import com.erdemer.e_ticaret.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel : ViewModel() {
    private val networkHelper = NetworkHelper()
    val productDetailResponseList = MutableLiveData<ProductDetailResponse>()

    var localProduct: ProductDetailResponse? = null
    val buttonEnable = MutableLiveData<Boolean>(false)


    fun getProductDetails(productId: Int) {
        networkHelper.productService?.getProductDetails(productId)
            ?.enqueue(object : Callback<ProductDetailResponse> {
                override fun onResponse(
                    call: Call<ProductDetailResponse>,
                    response: Response<ProductDetailResponse>
                ) {
                    response.body().let { product ->
                        localProduct = product

                        localProduct?.sizes?.forEach { size ->
                            if (localProduct?.sizeModel == null)
                                localProduct!!.sizeModel = arrayListOf()
                            localProduct?.sizeModel?.add(SizeModel(size, false))
                        }

                        productDetailResponseList.value = localProduct
                    }
                }

                override fun onFailure(call: Call<ProductDetailResponse>, t: Throwable) {
                    Log.e(Constants.PRODUCT_DETAIL_VIEW_MODEL_TAG, "Data couldn't be taken!")
                }

            })


    }

    fun checkButtonEnable() {
        localProduct?.let { localProduct ->
            if (!localProduct.sizeModel.isNullOrEmpty()) {
                localProduct.sizeModel.forEach {
                    if (it.isSelected) {
                        buttonEnable.value = true
                        return@forEach
                    }
                }
            }
        }
    }
}