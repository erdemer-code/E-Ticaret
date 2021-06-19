package com.erdemer.e_ticaret.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erdemer.e_ticaret.model.Category
import com.erdemer.e_ticaret.model.Product
import com.erdemer.e_ticaret.model.ProductResponse
import com.erdemer.e_ticaret.network.NetworkHelper
import com.erdemer.e_ticaret.util.Constants
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val networkHelper = NetworkHelper()
    val productResponseList = MutableLiveData<ProductResponse>()


    fun getAllProducts(){
        networkHelper.productService?.getMainProducts()?.enqueue(object :
            Callback<ProductResponse> {
            var mainTitle:String = ""
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                response.body()?.let {
                    productResponseList.value = it
                }

            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e(Constants.HOME_VIEW_MODEL_TAG,"Data couldn't be taken!")
            }
        })
    }


    }


