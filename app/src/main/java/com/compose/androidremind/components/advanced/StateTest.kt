package com.compose.androidremind.components.advanced

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun StateTest() {
    var name by remember { mutableStateOf("admin") }
//    name=name.run { "administrate" }
    fun setName(s: String) = run { name = s }
    Son(name, setName = {
        setName(it)
    })
}

@Composable
fun Son(name: String, setName: (String) -> Unit) {
    Column {
        Text(name)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(name, onValueChange = setName)
    }
}