package com.blaise.budgetier.ui.theme.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.R
import com.blaise.budgetier.ui.theme.MoneyGreen

@Composable
private fun Intermediate_Screen(navController: NavHostController) {
    Column (modifier = Modifier.fillMaxSize()){
        DrawerHeader()
    }
}

@Composable
private fun DrawerHeader(modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MoneyGreen)
            .height(192.dp)
            .padding(16.dp)
    ){
        Image(
            painter = painterResource( id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = "Budgetier",
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Preview
@Composable
private fun IntermediatePreview() {
    Intermediate_Screen(rememberNavController())
}