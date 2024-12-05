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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

@Preview(showBackground = true)
@Composable
fun TodoTestModel(todoViewM: TodoViewModel = viewModel()) {
    val todoUiState by todoViewM.uiState.collectAsState()
    fun checkDuplicate(it2: String): Boolean {
        val c = todoUiState.find { it.name == it2 }
        println("c$c")
        return c == null
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
                val text = todoViewM.inputStr
                TextField(value = text, onValueChange = {
                    todoViewM.changeStr(it)
                }, textStyle = TextStyle(fontSize = 16.sp, color = Color.Black))
                TextButton(onClick = {
                    if (text.isNotEmpty() && checkDuplicate(text))
//                        list += TodoCls(Date().time.toString(), text, false)
                        todoViewM.add(TodoClsR(Date().time.toString(), text, false))
                }) {
                    Text("Add")
                }
            }
            LazyColumn {
                items(todoUiState.size) { ri ->
                    val l = todoUiState[ri]
                    ListItem(headlineContent = {
                        Text(l.name)
                    }, trailingContent = {
                        Checkbox(checked = l.f, onCheckedChange = { cf ->
                            // 该方法无效
                            /* list.forEach {
                                 if (it.id == l.id) it.f = cf
                             }
                             println("list $list")*/
                            // 直接匹配当前索引修改
//                            list[ri]=list[ri].copy(f=cf)
                            // id查找修改
                            val index = todoUiState.indexOfFirst { l.id == it.id }
                            if (index != -1) {
                                todoViewM.update(index, cf)
                            }
                            println("list ${todoUiState.joinToString()}")
                        })
                    })
                }
            }
        }
        Row {
            Text("总数 ${todoUiState.size}")
            Spacer(modifier = Modifier.width(20.dp))
            Text("已完成 ${todoViewM.doneSize()}")
        }
    }
}

class TodoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        mutableListOf(
            TodoClsR("1001", "吃饭2", false),
            TodoClsR("1002", "睡觉", true),
            TodoClsR("1003", "打豆豆", false)
        )
    )
    val uiState: StateFlow<List<TodoClsR>> = _uiState.asStateFlow()
    var inputStr by mutableStateOf("")
        private set

    fun changeStr(s: String) = run { inputStr = s }
    fun add(cls: TodoClsR) {
        _uiState.value.add(0, cls)
        changeStr("")
    }

    fun update(i: Int, f: Boolean) {
        // 无法触发页面重组
        /*val it = _uiState.value[i]
        _uiState.value[i] = it.copy(f = f)*/
        val tempL=_uiState.value.toMutableList()
        tempL[i] = tempL[i].copy(f)
        _uiState.value=tempL
    }

    fun del() {

    }

    fun doneSize(): Int = _uiState.value.fold(0) { pre, acc ->
        pre + if (acc.f) 1 else 0
    }
}