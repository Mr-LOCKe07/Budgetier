package com.blaise.budgetier.ui.theme.screens.intermediate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.R
import com.blaise.budgetier.ui.theme.MoneyGreen

@Composable
fun Intermediate_Screen(navController: NavHostController) {
    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(MoneyGreen)
            .fillMaxSize()
    ){
        Image(
            painter = painterResource( id = R.drawable.menu_logo ),
            contentDescription = "Menu icon by Icons8",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
private fun IntermediatePreview() {
    Intermediate_Screen(rememberNavController())
}