package com.compose.androidremind.net.retrofit

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    private const val baseUrl = "https://rerain.top/client/"
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    ).build()

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}