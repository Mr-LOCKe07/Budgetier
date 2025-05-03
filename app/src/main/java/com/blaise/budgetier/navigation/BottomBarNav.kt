package com.blaise.budgetier.navigation

import android.net.http.SslCertificate.saveState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.blaise.budgetier.ui.theme.MoneyGreen

fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem.Residence,
        BottomNavItem.Groceries,
        BottomNavItem.Automobile,
        BottomNavItem.Subscriptions
    )

    BottomNavigation(
        backgroundColor = MoneyGreen,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId { saveState = true}
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}