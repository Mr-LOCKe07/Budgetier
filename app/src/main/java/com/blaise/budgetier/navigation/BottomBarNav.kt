package com.blaise.budgetier.navigation

import androidx.compose.runtime.getValue
import androidx.navigation.navOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blaise.budgetier.ui.theme.MoneyGreen

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Residence", Icons.Default.Home, ROUTE_HOME),
        BottomNavItem("Groceries", Icons.Default.ShoppingCart, ROUTE_GROCERIES),
        BottomNavItem("Auto", Icons.Default.DirectionsCar, ROUTE_AUTOMOBILE),
        BottomNavItem("Sub", Icons.Default.CreditCard, ROUTE_SUBSCRIPTIONS),
        BottomNavItem("Menu", Icons.Default.Menu, ROUTE_MENU)
    )

    NavigationBar(containerColor = MoneyGreen){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route, navOptions {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        })
                    }
                }
            )
        }
    }
}