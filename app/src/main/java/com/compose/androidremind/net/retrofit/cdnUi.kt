package com.compose.androidremind.net.retrofit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CdnUi(modifier: Modifier = Modifier, vm: cdnViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        vm.fetch()
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Retrofix")
        })
    }) {
        val f = vm.form
        Column(modifier.padding(it)) {
            Text("cdn Type")
            val list = vm.uiState.collectAsState()
            for ((k, _) in f) {
                TextField(prefix = {
                    Text(k)
                }, value = f[k]!!, onValueChange = { v -> vm.update(k, v) })
            }
            /*f.forEach{
                TextField(prefix = {
                    Text(it.key)
                }, value = f[it.key]!!, onValueChange = { v -> vm.update(it.key, v) })
            }*/
            TextButton({
                println(f)
            }) {
                Text("提交表单")
            }
            LazyColumn {
                items(list.value, { k -> k.id }) { c ->
                    ListItem(leadingContent = {
                        Text(c.id.toString())
                    }, headlineContent = {
                        Text(c.name)
                    })
                }
            }
        }
    }
}