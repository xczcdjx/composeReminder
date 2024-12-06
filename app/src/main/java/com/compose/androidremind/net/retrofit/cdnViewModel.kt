package com.compose.androidremind.net.retrofit

import android.util.Log
import androidx.lifecycle.ViewModel
import com.compose.androidremind.net.retrofit.util.safeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class cdnViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(mutableListOf<CType>())
    val uiState: StateFlow<List<CType>> = _uiState.asStateFlow()
    private val service = cdnService.instance()
    suspend fun fetch() {
//        val res = service.cdn()
        val res= safeService { service.cdn() }
        res.success?.let {
            _uiState.value=it.data.toMutableList()
        }?:run {
            println(res.error)
        }
        return
        try {
            val res = service.cdn()
            _uiState.value=res.data.toMutableList()
        } catch (e: Exception) {
            Log.d("Error ",e.message.toString())
        }
    }
}