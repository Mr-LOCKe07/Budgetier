package com.blaise.budgetier.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.Commute
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.ui.theme.MoneyGreen

data class BottomNavItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun BudgetNavigationDrawer(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("Main", Icons.Filled.ArrowCircleLeft, ROUTE_MAIN),
        BottomNavItem("Housing", Icons.Filled.Home, ROUTE_HOUSING),
        BottomNavItem("Transportation", Icons.Filled.Commute, ROUTE_TRANSPORTATION),
        BottomNavItem("Food", Icons.Filled.ShoppingCart, ROUTE_FOOD),
        BottomNavItem("Utilities", Icons.Filled.TipsAndUpdates, ROUTE_UTILITIES),
        BottomNavItem("Insurance", Icons.Filled.Money, ROUTE_INSURANCE),
        BottomNavItem("Healthcare", Icons.Filled.MedicalServices, ROUTE_HEALTHCARE),
        BottomNavItem("Personal&Lifestyle", Icons.Filled.CreditCard, ROUTE_PERSONAL_LIFESTYLE),
        BottomNavItem("DebtPayments", Icons.Filled.Payments, ROUTE_DEBTPAYMENTS),
        BottomNavItem("Savings&Investments", Icons.Filled.Savings, ROUTE_SAVINGS_INVESTMENTS),
        BottomNavItem("Miscellaneous", Icons.Filled.MiscellaneousServices, ROUTE_MISCELLANEOUS)
    )

    var expanded by remember { mutableStateOf(false) }
    var currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Column (modifier = Modifier.background(MoneyGreen)){
        NavigationBar (containerColor = Color.Transparent){
            items.take(4).forEach { item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(item.title) },
                    selected = currentRoute == item.route,
                    onClick = { navController.navigate(item.route) }
                )
            }

            NavigationBarItem(
                icon = { Icon(if (expanded) Icons.Filled.ExpandLess else Icons.Filled.Menu, "More") },
                label = { Text("More") },
                selected = false,
                onClick = { expanded = !expanded }
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .background(MoneyGreen)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items.drop(4).forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, null) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route)
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    BudgetNavigationDrawer(rememberNavController())
}