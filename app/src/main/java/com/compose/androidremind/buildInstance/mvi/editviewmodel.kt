package com.compose.androidremind.buildInstance.mvi

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.androidremind.buildInstance.mvi.entity.Todo
import com.compose.androidremind.buildInstance.mvi.service.TodoAbsCls
import com.compose.androidremind.buildInstance.mvi.service.TodoDao
import com.compose.androidremind.buildInstance.mvi.service.TodoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditViewModel(dao: TodoDao) : ViewModel() {
    private val _uiState = MutableStateFlow(
        mutableListOf<Todo>()
    )
    val uiState: StateFlow<List<Todo>> = _uiState.asStateFlow()
    var loading by mutableStateOf(true)
    // 数据持久化
    /*private val db= TodoAbsCls.getInstance(app.applicationContext)
    private val service= TodoService(db.dao())*/
    private val service=TodoService(dao)
    /*var toastState by mutableStateOf<String>("")
        private set*/
    // 使用MutableSharedFlow
    private val toastState= MutableSharedFlow<String>()
    val toast=toastState.asSharedFlow()

    fun fetchData(){
        viewModelScope.launch {
//            delay(2000)
            withContext(Dispatchers.IO){
                Thread.sleep(500)
            }
            loading=true
            /*_uiState.value= mutableListOf(
                Todo("1001", "吃饭2", false),
                Todo("1002", "睡觉", true),
                Todo("1003", "打豆豆", false)
            )*/
            _uiState.value=service.getAll().toMutableList()
            loading=false
        }
    }
    fun update(i: Int, f: Boolean) {
        val tempL = _uiState.value.toMutableList()
        tempL[i] = _uiState.value[i].copy(f=f)
        _uiState.value = tempL
        /*var index=0
        viewModelScope.launch {
                while (true){
                index++
                toastState.emit("${tempL[i].name.substring(0, minOf(8,tempL[i].name.length))} 已${if (f) "完成 $index" else "未完成 $index"}")
                delay(1000)
                Log.i("viewModel","$i")
            }
        }*/
        viewModelScope.launch {
            toastState.emit("${tempL[i].name.substring(0, minOf(8,tempL[i].name.length))} ${if (f) "已完成" else "未完成"}")
        }
    }
}