package com.erdemer.e_ticaret.network.service

import com.erdemer.e_ticaret.model.ProductDetailResponse
import com.erdemer.e_ticaret.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("main_page.php")
    fun getMainProducts():Call<ProductResponse>
    @GET("product_detail.php")
    fun getProductDetails(@Query("productId")productId: Int): Call<ProductDetailResponse>
}