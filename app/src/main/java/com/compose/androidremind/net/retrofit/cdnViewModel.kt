package com.compose.androidremind.net.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.androidremind.net.retrofit.util.safeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class cdnViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(mutableListOf<CType>())
    val uiState: StateFlow<List<CType>> = _uiState.asStateFlow()
    private val service = cdnService.instance()
    // 表单数据绑定
    var form by mutableStateOf(mutableMapOf<String, String>("username" to "", "password" to ""))
//    val form = mutableStateMapOf("username" to "", "password" to "")

    fun update(key: String, v: String) {
        form=form.toMutableMap().apply {this[key]=v  }
//        form[key] = v
    }
    suspend fun fetch() {
//        val res = service.cdn()
        val res= safeService { service.cdn() }
        res.success?.let {
            _uiState.value=it.data.toMutableList()
        }?:run {
            println(res.error)
        }
        /*try {
            val res = service.cdn()
            _uiState.value=res.data.toMutableList()
        } catch (e: Exception) {
            Log.d("Error ",e.message.toString())
        }*/
    }
}