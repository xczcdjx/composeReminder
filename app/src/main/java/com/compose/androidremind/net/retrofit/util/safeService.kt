package com.compose.androidremind.net.retrofit.util

import com.compose.androidremind.net.retrofit.ApiException
import java.io.IOException

data class ApiResponse<T>(val success: T?, val error: String? = null)

suspend fun <T> safeService(apiCall: suspend () -> T): ApiResponse<T> {
    return try {
        ApiResponse(apiCall())
    } catch (e: ApiException) {
        ApiResponse(null, e.message)
    } catch (e: IOException) {
        ApiResponse(null, "Network error")
    } catch (e: Exception) {
        ApiResponse(null, "Unknown error")
    }
}