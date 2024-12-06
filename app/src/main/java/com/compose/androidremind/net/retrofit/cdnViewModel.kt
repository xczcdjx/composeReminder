package com.compose.androidremind.net.retrofit

import androidx.lifecycle.ViewModel
import com.compose.androidremind.buildInstance.mvi.entity.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class cdnViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(mutableListOf<CType>())
    val uiState: StateFlow<List<CType>> = _uiState.asStateFlow()
    private val service = cdnService.instance()
    suspend fun fetch() {
        val res = service.cdn()
        if (res.code == 200) {
            _uiState.value=res.data.toMutableList()
        } else {
            println(res.msg)
        }
    }
}