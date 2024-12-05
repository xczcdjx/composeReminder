package com.compose.androidremind.buildInstance.mvi

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MviRouter() {
    val control = rememberNavController()
    NavHost(control, MviRoutes.Edit.name) {
        composable(MviRoutes.Edit.name) {
            Edit(){
                p->
                Log.i("router params",p?:"")
                control.navigate(MviRoutes.Control.name)
            }
        }
        composable(MviRoutes.Control.name) {
            Control()
        }
    }
}

sealed class MviRoutes(val name: String) {
    data object Edit : MviRoutes("Edit")
    data object Control : MviRoutes("Control")
}