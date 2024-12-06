package com.compose.androidremind.net.retrofit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
        Column(modifier.padding(it)) {
            Text("cdn Type")
            val list = vm.uiState.collectAsState()
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