package com.blaise.budgetier.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.ui.theme.screens.home.Home_Screen
import com.blaise.budgetier.ui.theme.screens.login.Login_Screen
import com.blaise.budgetier.ui.theme.screens.main.Main_Screen
import com.blaise.budgetier.ui.theme.screens.menu.Menu_Screen
import com.blaise.budgetier.ui.theme.screens.register.Register_Screen
import com.blaise.budgetier.ui.theme.screens.budget_services.Automobile_Screen
import com.blaise.budgetier.ui.theme.screens.budget_services.Groceries_Screen
import com.blaise.budgetier.ui.theme.screens.budget_services.Residence_Screen
import com.blaise.budgetier.ui.theme.screens.budget_services.Subscriptions_Screen
import com.blaise.budgetier.ui.theme.screens.splash.Splash_Screen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination:String = ROUTE_SPLASH) {
    NavHost(modifier = Modifier,
        navController = navController,
        startDestination = startDestination){
        composable(ROUTE_SPLASH) {
            Splash_Screen(navController)
        }
        composable(ROUTE_HOME){
            Home_Screen(navController)
        }
        composable(ROUTE_MAIN){
            Main_Screen(navController)
        }
        composable (ROUTE_MENU){
            Menu_Screen(navController)
        }
        composable (ROUTE_RESIDENCE){
            Residence_Screen(navController)
        }
        composable (ROUTE_AUTOMOBILE){
            Automobile_Screen(navController)
        }
        composable (ROUTE_GROCERIES){
            Groceries_Screen(navController)
        }
        composable (ROUTE_SUBSCRIPTIONS){
            Subscriptions_Screen(navController)
        }
        composable(ROUTE_LOGIN){
           Login_Screen(navController)
        }
        composable(ROUTE_REGISTER){
            Register_Screen(navController)
        }
    }
}