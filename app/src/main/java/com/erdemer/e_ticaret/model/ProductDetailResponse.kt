package com.erdemer.e_ticaret.model

data class ProductDetailResponse(
    val brandName: String?,
    val category: String?,
    val id: String?,
    val images: List<String>?,
    val latitude: String?,
    val longitude: String?,
    val price: String?,
    val priceDiscount: String?,
    val productDetailInfo: String?,
    val productName: String?,
    val sizes: List<String>?,
    var sizeModel: ArrayList<SizeModel> = arrayListOf()
)