package com.compose.androidremind.buildInstance.mvi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ControlViewmodel:ViewModel() {
    var textV by mutableStateOf("")
        private set
    fun updateTextV(t:String){
        textV=t
    }
}