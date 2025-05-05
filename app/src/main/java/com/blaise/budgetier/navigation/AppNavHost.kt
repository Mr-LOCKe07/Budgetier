package com.blaise.budgetier.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.model.SharedServiceViewModel
import com.blaise.budgetier.ui.theme.screens.home.Home_Screen
import com.blaise.budgetier.ui.theme.screens.login.Login_Screen
import com.blaise.budgetier.ui.theme.screens.main.Main_Screen
import com.blaise.budgetier.ui.theme.screens.menu.Menu_Screen
import com.blaise.budgetier.ui.theme.screens.register.Register_Screen
import com.blaise.budgetier.ui.theme.screens.services.Service_Screen
import com.blaise.budgetier.ui.theme.screens.splash.Splash_Screen

@Composable
fun NavGraph(
    viewModel: SharedServiceViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH,
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = "splash") {
        composable ("main"){
            Main_Screen(navController = navController, viewModel = viewModel)
        }
        composable(ROUTE_SPLASH) {
            Splash_Screen(navController)
        }
        composable(ROUTE_HOME){
            Home_Screen(navController)
        }
        composable (ROUTE_MENU){
            Menu_Screen(navController)
        }
        composable(ROUTE_LOGIN){
            Login_Screen(navController)
        }
        composable(ROUTE_REGISTER){
            Register_Screen(navController)
        }
        viewModel.services.forEach { service ->
            composable(service.route) {
                Service_Screen(route = service.route, viewModel = viewModel)
            }
        }
    }
}