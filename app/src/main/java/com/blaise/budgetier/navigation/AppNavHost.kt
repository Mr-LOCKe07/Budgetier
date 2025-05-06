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
import com.blaise.budgetier.ui.theme.screens.register.Register_Screen
import com.blaise.budgetier.ui.theme.screens.services.Food_Screen
import com.blaise.budgetier.ui.theme.screens.services.Transportation_Screen
import com.blaise.budgetier.ui.theme.screens.services.Housing_Screen
import com.blaise.budgetier.ui.theme.screens.splash.Splash_Screen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH,
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "splash") {
        composable ("main"){
            Main_Screen(navController)
        }
        composable(ROUTE_SPLASH) {
            Splash_Screen(navController)
        }
        composable(ROUTE_HOME){
            Home_Screen(navController)
        }
        composable(ROUTE_LOGIN){
            Login_Screen(navController)
        }
        composable(ROUTE_REGISTER){
            Register_Screen(navController)
        }
        composable(ROUTE_FOOD){
            Food_Screen(navController)
        }
        composable (ROUTE_TRANSPORTATION){
            Transportation_Screen(navController)
        }
        composable (ROUTE_HOUSING){
            Housing_Screen(navController)
        }
    }
}