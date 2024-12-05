package com.compose.androidremind.buildInstance.mvi.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.androidremind.buildInstance.mvi.ControlViewmodel
import com.compose.androidremind.buildInstance.mvi.EditViewModel
import com.compose.androidremind.buildInstance.mvi.service.TodoAbsCls

@Suppress("UNCHECKED_CAST")
class TodoViewModelFactory(private val ctx: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditViewModel::class.java)) {
            return EditViewModel(TodoAbsCls.getInstance(ctx).dao()) as T
        }
        else if (modelClass.isAssignableFrom(ControlViewmodel::class.java)) {
            return ControlViewmodel(TodoAbsCls.getInstance(ctx).dao()) as T
        }
        throw IllegalArgumentException("Unknown viewmodel class")
    }
}