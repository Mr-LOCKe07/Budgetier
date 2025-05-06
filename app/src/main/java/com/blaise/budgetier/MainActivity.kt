package com.blaise.budgetier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.navigation.NavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           NavGraph(navController = rememberNavController(),
               startDestination = "splash",
               modifier = Modifier)
        }
    }
}
