package com.blaise.budgetier.ui.theme.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.R
import com.blaise.budgetier.navigation.ROUTE_HOME
import kotlinx.coroutines.delay

@Composable
fun Splash_Screen(navController: NavHostController) {
  LaunchedEffect(Unit) {
      delay(3000)
      navController.navigate(ROUTE_HOME)
  }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.splashscreen),
            contentDescription = "Budgetier Splash Screen",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun SplashPreview() {
    Splash_Screen(rememberNavController())
}