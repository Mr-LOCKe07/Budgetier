package com.blaise.budgetier.ui.theme.screens.main

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.data.Service
import com.blaise.budgetier.model.SharedServiceViewModel
import com.blaise.budgetier.navigation.BottomBar
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.NewOrange
import com.blaise.budgetier.ui.theme.YellowElegance
import com.blaise.budgetier.ui.theme.screens.addition.AddServiceDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_Screen(
    navController: NavController,
    viewModel: SharedServiceViewModel
) {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)
    val name = sharedPref.getString("full_name", "User")
    val email = sharedPref.getString("email", "No email")

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold (
        bottomBar = {
            BottomBar(navController = navController as NavHostController, serviceViewModel = viewModel)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true  },
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
                .verticalScroll(rememberScrollState())
                .background(Color.White)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User Icon",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .background(YellowElegance, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(name ?: "User", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(email ?: "No email", fontSize = 14.sp)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Text("Your Services", fontSize = 28.sp, color = MoneyGreen)

            if (viewModel.services.isEmpty()) {
                Text(
                    text = "No services yet",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp
                )
            } else {
                viewModel.services.forEach { service ->
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { navController.navigate(service.route)},
                    ){
                        Row (
                            modifier = Modifier.padding(16.dp)){
                            Icon(service.icon, contentDescription = null)
                            Spacer(Modifier.width(12.dp))
                            Text(service.name, fontSize = 20.sp)
                        }
                    }
            }

            if (showAddDialog) {
                AddServiceDialog(
                    onDismiss1 = rememberNavController(),
                    onDismiss = { showAddDialog = false }
                ) { serviceName, icon, budget ->
                    val route = serviceName.lowercase().replace(" ", "_")
                    viewModel.addService(Service(serviceName, route, icon, budget))
                    showAddDialog = false
                }
            }
        }
    }
}
}
