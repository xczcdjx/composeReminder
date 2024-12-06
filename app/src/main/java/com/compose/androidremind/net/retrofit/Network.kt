package com.compose.androidremind.net.retrofit

import android.util.Log
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

object Network {
    private const val baseUrl = "https://rerain.top/client/"

    // 创建一个全局拦截器
    private val globalInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            try {
                val response = chain.proceed(request)
                // 检查响应是否成功
                if (listOf(200,201).contains(response.code)) {
                    // 全局成功处理，例如记录日志或统计数据
                    println("Request succeeded: ${response.code}")
                    return response
                } else {
                    // 全局错误处理
                    val errorBody = response.body?.string() // 获取错误的响应体内容
                    println("Request error: $errorBody")
                    val json = JSONObject(errorBody) // 使用 org.json.JSONObject
                    val msg=json.optString("msg", "Unknown error")
                    // 构建自定义异常并抛出
                    throw ApiException(
                        code = response.code,
                        message = msg
                    )
                }
            } catch (e: IOException) {
                // 网络错误的全局处理
                Log.e("Network request fail",e.message.toString())
                throw e // 重新抛出异常
            }
        }
    }

    // 创建 OkHttpClient，并添加日志拦截器
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 设置日志级别：NONE, BASIC, HEADERS, BODY
        }).addInterceptor(globalInterceptor)
        .build()
    private val retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(
        MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    ).build()

    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}
class ApiException(val code: Int, message: String) : IOException(message)