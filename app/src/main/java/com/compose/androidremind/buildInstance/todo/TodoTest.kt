package com.compose.androidremind.buildInstance.todo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Date


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TodoTest() {
    var text by remember { mutableStateOf("") }/*val list = remember {
        mutableListOf<TodoCls>(listOf())
    }*/
    var list by remember {
        mutableStateOf(
            listOf(
                TodoCls("1001", "吃饭2", false),
                TodoCls("1002", "睡觉", true),
                TodoCls("1003", "打豆豆", false)
            )
        )
    }

    fun checkDuplicate(it2: String): Boolean {
//        val c=list.map { it.name }.contains(it)
        val c=list.find { it.name==it2 }
        println("c$c")
        return c==null
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "TodoList ...", fontSize = 20.sp, textAlign = TextAlign.Center)
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(400.dp)
                .border(
                    BorderStroke(
                        1.dp, Color.Black
                    ), shape = RoundedCornerShape(5.dp)
                )
                .clip(RoundedCornerShape(5.dp))
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                TextField(value = text, onValueChange = {
                    text = it
                }, textStyle = TextStyle(fontSize = 16.sp, color = Color.Black))
                TextButton(onClick = {
                    if (text.isNotEmpty() && checkDuplicate(text))
//                        list += TodoCls(Date().time.toString(), text, false)
                        list=listOf(TodoCls(Date().time.toString(), text, false))+list
                    text=""
                }) {
                    Text("Add")
                }
            }
            LazyColumn {
                items(list.size){
                    ri->
                    val l=list[ri]
                    ListItem(headlineContent = {
                        Text(l.name)
                    }, trailingContent = {
                        Checkbox(checked = l.f, onCheckedChange = {
                            list = list.mapIndexed { ci, l2 ->
                                if (ri == ci) l2.copy(f = it)
                                else l2
                            }
                        })
                    })
                }
            }
        }
        Row {
            Text("总数 ${list.size}")
            Spacer(modifier = Modifier.width(20.dp))
            val doneNum = list.fold(0) {
                acc,todo -> acc+if (todo.f) 1 else 0 }
//            val count=list.count { it.f }
            Text("已完成 $doneNum")
        }
    }
}