package com.compose.androidremind.net.retrofit

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    private const val baseUrl = "https://rerain.top/client/"

    // 创建 OkHttpClient，并添加日志拦截器
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 设置日志级别：NONE, BASIC, HEADERS, BODY
        })
        .build()
    private val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(
        MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    ).build()

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}