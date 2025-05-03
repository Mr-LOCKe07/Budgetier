package com.blaise.budgetier.ui.theme.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.R
import com.blaise.budgetier.navigation.ROUTE_LOGIN
import com.blaise.budgetier.navigation.ROUTE_REGISTER
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.YellowElegance

@Composable
fun Home_Screen(navController: NavHostController) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MoneyGreen)
    ){
        Text(text = "Welcome to,",
            fontSize = 30.sp,
            color = Color.Black,
            fontFamily = FontFamily.Cursive
        )

        Text(text = "BUDGETIER",
            fontSize = 40.sp,
            color = YellowElegance,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.motto),
            contentDescription = "Budgetier Logo",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(ROUTE_LOGIN)},
            border = BorderStroke(2.dp, YellowElegance),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier.width(300.dp)
        ) {
            Text(text = "Login",
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(ROUTE_REGISTER)},
            border = BorderStroke(2.dp, YellowElegance),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier.width(300.dp)
        ) {
            Text(text = "Register",
                color = Color.Black,
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif)
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home_Screen(rememberNavController())
}