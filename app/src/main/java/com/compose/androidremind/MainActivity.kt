package com.compose.androidremind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.androidremind.buildInstance.game.GameScreen
import com.compose.androidremind.buildInstance.mvi.Edit
import com.compose.androidremind.buildInstance.mvi.MviRouter
import com.compose.androidremind.buildInstance.todo.TodoTestModel
import com.compose.androidremind.ui.theme.AndroidRemindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidRemindTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    *//*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*//*
                    *//*Column {
                        Spacer(modifier = Modifier.padding(innerPadding))
//                    TodoTest()
//                        TodoTestOptimize()
                        FlowLayoutTest()
                        Spacer(modifier = Modifier.height(10.dp))
                        StateTest()
                    }*//*
                    Spacer(modifier = Modifier.padding(innerPadding))
//                    GameScreen()
                    TodoTestModel()
                }*/
                MviRouter()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidRemindTheme {
        Greeting("Android")
    }
}