package com.compose.androidremind.buildInstance.mvi

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.androidremind.buildInstance.mvi.factory.TodoViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// viewmodel 需要用到context,如果通过直接实例化ViewModel传入LocalContext.current 会导致state失效
// 解决办法: 1.使用AndroidViewModel,2.通过viewModelProvider.Factory 构造 ViewModel,3.通过Hilt依赖注入
fun Edit(modifier: Modifier = Modifier,goC:(String?)->Unit) {
    val ctx= LocalContext.current
    val vm: EditViewModel = viewModel(factory = TodoViewModelFactory(ctx))
    val editUiState by vm.uiState.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("List")
        })
    }, floatingActionButton = {
        IconButton(onClick = {
            goC(null)
        }) {
            Icon(Icons.Default.Add, null)
        }
    }) { pad ->
//        Spacer(modifier.padding(pad))
        /*Row(
            modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("no data")
        }*/
//        val ctx= LocalContext.current
        val toast=vm.toast
        val lifecycleOwner= LocalLifecycleOwner.current
        LaunchedEffect(Unit) {
            vm.fetchData()
        }
        LaunchedEffect(vm.toast,lifecycleOwner) {
//            if (vm.toastState.isNotBlank()) Toast.makeText(ctx,vm.toastState,Toast.LENGTH_SHORT).show()
            // 在start生命周期之后执行
           lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
               toast.collect{
                   Log.i("ListScreen",it)
                   Toast.makeText(ctx,it,Toast.LENGTH_SHORT).show()
               }
           }
        }
        if (vm.loading){
            Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }else{
            if (editUiState.isEmpty()){
                Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text("no Data")
                }
            }else {
                Surface(
                    modifier = Modifier
                        .padding(top = pad.calculateTopPadding())
                        .border(BorderStroke(1.dp, Color.Black))
                        .height(400.dp)
                ) {
                    LazyColumn {
                        items(editUiState, key = { it.id }) { cur ->
                            ListItem(modifier = modifier.clickable {
                                goC(cur.id)
                            }, headlineContent = {
                                Text(cur.name)
                            }, supportingContent = {
                                Text(cur.time?:"")
                            }, trailingContent = {
                                Checkbox(cur.f, { f ->
//                            f->
                                    val i = editUiState.indexOfFirst { it.id == cur.id }
                                    if (i != -1) {
                                        vm.update(i, f)
                                    }
                                })
                            })
                        }
                    }
                }
            }
        }
    }
}