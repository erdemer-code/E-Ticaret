package com.erdemer.e_ticaret.network

import com.erdemer.e_ticaret.BuildConfig
import com.erdemer.e_ticaret.network.service.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkHelper {
    var productService: ProductService? = null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://alpkaraosmanoglu.com.tr/appcent/")
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productService = retrofit.create(ProductService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(45, TimeUnit.SECONDS)
        httpClient.readTimeout(45, TimeUnit.SECONDS)
        httpClient.writeTimeout(45, TimeUnit.SECONDS)
        httpClient.addInterceptor(createHttpLoggingInterceptor(BuildConfig.DEBUG))
        return httpClient.build()
    }

    private fun createHttpLoggingInterceptor(debugMode: Boolean): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (debugMode) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

}