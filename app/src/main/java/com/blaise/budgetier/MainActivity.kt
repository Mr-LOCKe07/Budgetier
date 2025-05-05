package com.blaise.budgetier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.blaise.budgetier.model.SharedServiceViewModel
import com.blaise.budgetier.navigation.NavGraph
import com.blaise.budgetier.ui.theme.BudgetierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetierTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(viewModel = SharedServiceViewModel(),
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
