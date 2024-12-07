package com.compose.androidremind.advance.shareviewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// 状态提升实现数据共享
@Composable
fun ShareModel(modifier: Modifier = Modifier) {
    val control = rememberNavController()
    val vm: ShareViewModel = viewModel()
    NavHost(control, "A") {
        composable("A") {
            A(vm) {
                control.navigate("B")
            }
        }
        composable("B") {
            B(vm, goA = {
                control.popBackStack()
            }) {
                control.navigate("A")
            }
        }
    }
}

class ShareViewModel : ViewModel() {
    var str by mutableStateOf("")
        private set

    fun update(s: String) {
        str = s
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun A(vm: ShareViewModel, modifier: Modifier = Modifier, goB: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("A")
        })
    }) { pad ->
        Column(modifier.padding(pad)) {
            Text("str ${vm.str}")
            TextButton({
                vm.update(vm.str + "1")
            }) {
                Text("Change Text")
            }
            TextButton(goB) {
                Text("Go B")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun B(
    vm: ShareViewModel,
    modifier: Modifier = Modifier,
    goA: () -> Unit,
    back: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("B")
        })
    }) { pad ->
        Column(modifier.padding(pad)) {
            Text("str ${vm.str}")
            TextButton({
                vm.update(vm.str + "999")
            }) {
                Text("Change Text")
            }
            Row {
                TextButton(back) {
                    Text("Back")
                }
                TextButton(goA) {
                    Text("Go B")
                }
            }
        }
    }
}