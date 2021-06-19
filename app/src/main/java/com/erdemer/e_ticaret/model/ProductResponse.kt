package com.erdemer.e_ticaret.model

data class ProductResponse(
    val categoryList: List<Category>,
    val mainTitle: String?,
    val productList: List<Product>?,
    val subTitle: String?
)