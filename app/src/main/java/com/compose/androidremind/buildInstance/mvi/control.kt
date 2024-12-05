package com.compose.androidremind.buildInstance.mvi

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.androidremind.buildInstance.mvi.factory.TodoViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Control(id:String?,modifier: Modifier = Modifier) {
    val ctx= LocalContext.current
    val vm: ControlViewmodel = viewModel(factory = TodoViewModelFactory(ctx))
    LaunchedEffect(Unit) {
        vm.fetchData(id)
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Control")
        }, actions = {
            TextButton(onClick = {
                vm.onSave()
            }) {
                Text("Save")
            }
        })
    }) { pad ->
//        Spacer(modifier.padding(pad))
        TextField(vm.textV.name, { vm.updateTextV(it) }, modifier = modifier.padding(pad).fillMaxSize())
    }
}