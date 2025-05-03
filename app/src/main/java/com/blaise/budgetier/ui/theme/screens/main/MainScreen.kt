package com.blaise.budgetier.ui.theme.screens.main

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.R
import com.blaise.budgetier.navigation.BottomBar
import com.blaise.budgetier.navigation.ROUTE_AUTOMOBILE
import com.blaise.budgetier.navigation.ROUTE_MENU
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.YellowElegance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_Screen(navController: NavHostController) {
    Scaffold (
        bottomBar = { BottomBar(navController) }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(MoneyGreen)
        ){
            val context = LocalContext.current
            val sharedPref = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)

            val name = sharedPref.getString("full_name", "User")
            val email = sharedPref.getString("email", "No email")

            Column(
                modifier = Modifier
                    .background(MoneyGreen)
                    .verticalScroll(rememberScrollState())
            ) {
                Box {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                        colors = CardDefaults.cardColors(YellowElegance)
                    ) {
                        TopAppBar(
                            title = { Text(text = "Welcome") },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigate(ROUTE_MENU) }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Taskbar"
                                    )
                                }
                            }
                        )
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, top = 20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Person Icon",
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(Color.White, CircleShape)
                                    .padding(8.dp)
                            )

                            Spacer(modifier= Modifier.width(12.dp))

                            Column {
                                Text(
                                    text = "Hello $name",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 50.sp
                                )
                                Text(
                                    text = "$email",
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                }

                Box (
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "BUDGET SERVICES",
                        fontSize = 50.sp,
                        fontFamily = FontFamily.Serif,
                        color = YellowElegance
                    )
                }

                Row(modifier = Modifier.padding(20.dp)) {
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(180.dp)
                            .clickable { /*TODO*/ },
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_food_receiver_24),
                                contentDescription = "Food Receiver icon by Icons8",
                                modifier = Modifier.size(100.dp)
                            )
                            Text(
                                text = "Groceries",
                                fontSize = 15.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(50.dp))

                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(180.dp)
                            .clickable { navController.navigate(ROUTE_AUTOMOBILE) },
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_car_24),
                                contentDescription = "Car icon by Icons8",
                                modifier = Modifier.size(100.dp)
                            )
                            Text(
                                text = "Automobile",
                                fontSize = 15.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(modifier = Modifier.padding(20.dp)) {
                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(180.dp)
                            .clickable { /*TODO*/ },
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_house_24),
                                contentDescription = "House icon by Icons8",
                                modifier = Modifier.size(100.dp)
                            )
                            Text(
                                text = "Residence",
                                fontSize = 15.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(50.dp))

                    Card(
                        modifier = Modifier
                            .width(150.dp)
                            .height(180.dp)
                            .clickable { /*TODO*/ },
                        elevation = CardDefaults.cardElevation()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.icons8_subscription_24),
                                contentDescription = "Subscription icon by Icons8",
                                modifier = Modifier.size(100.dp)
                            )
                            Text(
                                text = "Subscriptions",
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    Main_Screen(rememberNavController())
}