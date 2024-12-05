package com.compose.androidremind.buildInstance.mvi

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.androidremind.buildInstance.mvi.entity.Todo
import com.compose.androidremind.buildInstance.mvi.service.TodoDao
import com.compose.androidremind.buildInstance.mvi.service.TodoService
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class ControlViewmodel(private val dao: TodoDao):ViewModel() {
    private val service=TodoService(dao)
    var textV by mutableStateOf(Todo("",""))
        private set
    fun updateTextV(t:String){
        textV=textV.copy(name = t)
    }
    // 时间格式
    private val dateFormatter=SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    fun onSave(){
        if (textV.name.isNotBlank()) viewModelScope.launch {
            if (textV.id.isBlank()){
                textV.id=Random.nextInt(100000).toString()
                textV.time=dateFormatter.format(Date())
                service.insert(textV)
                Log.d("TAG", "onSave: $textV")
            }else{
                service.update(textV)
                Log.d("TAG", "onSave33333: $textV")
            }
            textV=textV.copy(name="")
        }
    }
}