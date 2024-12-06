package com.compose.androidremind.buildInstance.mvi

import android.util.Log
import androidx.compose.runtime.Composable
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
                control.navigate(MviRoutes.Control(p).name)
            }
        }
        composable(MviRoutes.Control("{${Params.Id.key}}").name) {backStackEntry->
            val id=backStackEntry.arguments?.getString(Params.Id.key)
            Control(if (id == "null") null else id)
        }
    }
}

sealed class MviRoutes(val name: String) {
    data object Edit : MviRoutes("Edit")
    data class Control(val id: String?) : MviRoutes("Control/${id}?")
}
sealed class Params(val key:String){
    object Id:Params("id")
}