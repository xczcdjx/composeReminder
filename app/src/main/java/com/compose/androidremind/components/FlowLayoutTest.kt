package com.compose.androidremind.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun FlowLayoutTest() {
    val series = List(5) { (it + 97).toChar() }.map { "$it$it$it" }
    val leadIcon:@Composable ()->Unit={
        Icon(Icons.Default.Check,null)
    }
    // 多选案例
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        series.forEach {
            var selected by remember { mutableStateOf(false) }
            FilterChip(
                selected = selected,
                onClick = {
                    selected = !selected
                },
                label = {
                    Text(it)
                },
                leadingIcon = if (selected) leadIcon else null
            )
        }
    }
}