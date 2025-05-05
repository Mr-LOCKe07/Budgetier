package com.blaise.budgetier.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.blaise.budgetier.model.SharedServiceViewModel
import com.blaise.budgetier.ui.theme.MoneyGreen

@Composable
fun BottomBar(navController: NavHostController, serviceViewModel: SharedServiceViewModel) {
    NavigationBar(containerColor = MoneyGreen){
        serviceViewModel.services.forEach { service ->
            NavigationBarItem(
                selected = false,
                onClick = { navController.navigate(service.route) },
                icon = {
                    Icon(service.icon, contentDescription = service.name)
                },
                label = { Text(service.name) }
            )
        }
    }
}