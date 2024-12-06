package com.compose.androidremind.storage.dataStore

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataStoreDemo(modifier: Modifier = Modifier) {
    val storage = StoreManager(LocalContext.current)
    val scope = rememberCoroutineScope()
    val saveName = storage.getString.collectAsState("");
//    val name by remember { derivedStateOf { saveName.value?:"" } }
    var name by remember { mutableStateOf("")}
    LaunchedEffect(Unit) {
        Log.d("****DataStore",saveName.value?:"1111")
        delay(5)
        name = saveName.value ?: ""
    }
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("DataStore Demo", modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        })
    }) { pad ->
        Column(modifier.padding(pad)) {
            Text(buildString {
                append("name: ")
                append(name)
            })
            TextField(name, {
                name=it
                scope.launch {
                    storage.setString(it)
                }
            })
            Text(buildString {
                append("保存的name: ")
                append(saveName.value)
            })
        }
    }
}