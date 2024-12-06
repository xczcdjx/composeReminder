package com.compose.androidremind.net.retrofit

import retrofit2.http.GET

interface cdnService {
    @GET("cdn")
    suspend fun cdn():cdnEntity
    companion object{
        fun instance():cdnService{
            return Network.createService(cdnService::class.java)
        }
    }
}