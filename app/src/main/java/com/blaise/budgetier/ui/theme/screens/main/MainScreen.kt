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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.blaise.budgetier.data.Service
import com.blaise.budgetier.navigation.BottomBar
import com.blaise.budgetier.navigation.ROUTE_AUTOMOBILE
import com.blaise.budgetier.navigation.ROUTE_MENU
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.NewOrange
import com.blaise.budgetier.ui.theme.YellowElegance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_Screen(navController: NavHostController) {
    Scaffold (
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(MoneyGreen)
        ) {
            val context = LocalContext.current
            val sharedPref = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)

            val name = sharedPref.getString("full_name", "User")
            val email = sharedPref.getString("email", "No email")

            val services = remember { mutableStateListOf<Service>() }
            var showAddDialog by remember { mutableStateOf(false) }

            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { showAddDialog = true },
                        containerColor = NewOrange
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Service")
                    }
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    val totalBudget = services.sumOf { it.budget }
                    Text("Total Budget: KES $totalBudget", fontWeight = FontWeight.Bold, fontSize = 20.sp)

                    Spacer(Modifier.height(16.dp))

                    services.forEach { index, service ->
                        Card (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(16.dp)
                        ){
                            Row (
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(service.icon, contentDescription = null)
                                Text(service.name, fontWeight = FontWeight.Bold)
                                Text("KES ${service.budget}")
                                IconButton(onClick = { service.removeAt(index) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    }

                    if (showAddDialog) {
                        AddS
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