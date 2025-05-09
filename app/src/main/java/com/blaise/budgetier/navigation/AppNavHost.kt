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
import com.blaise.budgetier.ui.theme.screens.services.DebtPayments_Screen
import com.blaise.budgetier.ui.theme.screens.services.Food_Screen
import com.blaise.budgetier.ui.theme.screens.services.Healthcare_Screen
import com.blaise.budgetier.ui.theme.screens.services.Transportation_Screen
import com.blaise.budgetier.ui.theme.screens.services.Housing_Screen
import com.blaise.budgetier.ui.theme.screens.services.Insurance_Screen
import com.blaise.budgetier.ui.theme.screens.services.Miscellaneous_Screen
import com.blaise.budgetier.ui.theme.screens.services.Personal_Lifestyle_Screen
import com.blaise.budgetier.ui.theme.screens.services.Savings_Investments_Screen
import com.blaise.budgetier.ui.theme.screens.services.Utilities_Screen
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
        composable(ROUTE_HOME) {
            Home_Screen(navController)
        }
        composable(ROUTE_LOGIN) {
            Login_Screen(navController)
        }
        composable(ROUTE_REGISTER) {
            Register_Screen(navController)
        }
        composable (ROUTE_HOUSING) {
            Housing_Screen(navController)
        }
        composable (ROUTE_TRANSPORTATION) {
            Transportation_Screen(navController)
        }
        composable(ROUTE_FOOD) {
            Food_Screen(navController)
        }
        composable (ROUTE_UTILITIES) {
            Utilities_Screen(navController)
        }
        composable(ROUTE_INSURANCE) {
            Insurance_Screen(navController)
        }
        composable(ROUTE_HEALTHCARE) {
            Healthcare_Screen(navController)
        }
        composable(ROUTE_PERSONAL_LIFESTYLE){
            Personal_Lifestyle_Screen(navController)
        }
        composable(ROUTE_DEBTPAYMENTS) {
            DebtPayments_Screen(navController)
        }
        composable(ROUTE_SAVINGS_INVESTMENTS) {
            Savings_Investments_Screen(navController)
        }
        composable(ROUTE_MISCELLANEOUS) {
            Miscellaneous_Screen(navController)
        }
    }
}