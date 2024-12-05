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
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Control(modifier: Modifier = Modifier, vm: ControlViewmodel = viewModel()) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Control")
        }, actions = {
            TextButton(onClick = {

            }) {
                Text("Save")
            }
        })
    }) { pad ->
//        Spacer(modifier.padding(pad))
        TextField(vm.textV, { vm.updateTextV(it) }, modifier = modifier.padding(pad).fillMaxSize())
    }
}