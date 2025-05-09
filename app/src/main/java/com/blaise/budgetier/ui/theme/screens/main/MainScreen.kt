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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.navigation.ROUTE_DEBTPAYMENTS
import com.blaise.budgetier.navigation.ROUTE_FOOD
import com.blaise.budgetier.navigation.ROUTE_HEALTHCARE
import com.blaise.budgetier.navigation.ROUTE_HOUSING
import com.blaise.budgetier.navigation.ROUTE_INSURANCE
import com.blaise.budgetier.navigation.ROUTE_MISCELLANEOUS
import com.blaise.budgetier.navigation.ROUTE_PERSONAL_LIFESTYLE
import com.blaise.budgetier.navigation.ROUTE_SAVINGS_INVESTMENTS
import com.blaise.budgetier.navigation.ROUTE_TRANSPORTATION
import com.blaise.budgetier.navigation.ROUTE_UTILITIES
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.NewOrange
import com.blaise.budgetier.ui.theme.YellowElegance
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment

data class ServiceItem(
    val name: String,
    val icon: ImageVector,
    val route: String,
    var budget: Double = 0.0,
    var isActive: Boolean = false
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_Screen(navController: NavController) {

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)
    val name = sharedPref.getString("full_name", "User")
    val email = sharedPref.getString("email", "No email")

    var services by remember {
        mutableStateOf(
            listOf(
                ServiceItem("Housing", Icons.Filled.Home, ROUTE_HOUSING),
                ServiceItem("Transportation", Icons.Filled.DirectionsCar, ROUTE_TRANSPORTATION),
                ServiceItem("Food", Icons.Filled.ShoppingCart, ROUTE_FOOD),
                ServiceItem("Utilities", Icons.Filled.TipsAndUpdates, ROUTE_UTILITIES),
                ServiceItem("Insurance", Icons.Filled.Money, ROUTE_INSURANCE),
                ServiceItem("Healthcare", Icons.Filled.MedicalServices, ROUTE_HEALTHCARE),
                ServiceItem("Personal&Lifestyle", Icons.Filled.CreditCard, ROUTE_PERSONAL_LIFESTYLE),
                ServiceItem("DebtPayments", Icons.Filled.Payments, ROUTE_DEBTPAYMENTS),
                ServiceItem("Savings&Investments", Icons.Filled.Savings, ROUTE_SAVINGS_INVESTMENTS),
                ServiceItem("Miscellaneous", Icons.Filled.MiscellaneousServices, ROUTE_MISCELLANEOUS)
            )
        )
    }

    val totalBudget = remember(services) {
        services.filter { it.isActive }.sumOf { it.budget }
    }
    val activeCount = remember(services) {
        services.count { it.isActive}
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ){
        TopAppBar(
            title = { Text("Budgetier") },
            colors = TopAppBarDefaults.topAppBarColors(MoneyGreen)
        )

        LazyColumn {
            item {
                Card (
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp),
                    colors = CardDefaults.cardColors(NewOrange)
                ){
                    Row (
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "User",
                            modifier = Modifier
                                .size(70.dp)
                                .background(YellowElegance, shape = CircleShape)
                                .padding(10.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                name ?: "User",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                email ?: "No email",
                                fontSize = 16.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(Color(0xFFF5F5F5)),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column (modifier = Modifier.padding(16.dp)) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Total Budget",
                                style = MaterialTheme.typography.titleMedium,
                                color = MoneyGreen
                            )
                            Text(
                                "$activeCount active",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "KES ${"%.2f".format(totalBudget)}",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(
                        "Budget Services",
                        modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = MoneyGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            item {
                if (services.any { !it.isActive }) {
                    Text(
                        "Available Categories",
                        modifier = Modifier.padding(start = 24.dp, top = 16.dp, bottom = 8.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = MoneyGreen.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            items (services.filter { !it.isActive }) { service ->
                ServiceCard(
                    service = service,
                    onClick = { navController.navigate(service.route) },
                    onBudgetChange = { updatedService ->
                        services = services.map {
                            if (it.name == updatedService.name) {
                                updatedService
                            } else {
                                it
                            }
                        }
                    }
                )
            }

            item{
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun ServiceCard(
    service: ServiceItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onBudgetChange: (ServiceItem) -> Unit
) {
    var showBudgetInput by remember { mutableStateOf(false) }
    var budgetInput by remember { mutableStateOf(service.budget.toString()) }

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable ( onClick = onClick ),
        colors = CardDefaults.cardColors(containerColor = if (service.isActive) Color(0xFFE8F5E9) else Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ){
        Column (modifier = Modifier.padding(16.dp)){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(
                    service.icon,
                    contentDescription = service.name,
                    modifier = Modifier.size(28.dp),
                    tint = if (service.isActive) MoneyGreen else Color.Gray
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                if (service.isActive) {
                    Text(
                        "KES ${"%.2f".format(service.budget)}",
                        color = MoneyGreen,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "View details",
                tint = Color.Gray.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    Main_Screen(rememberNavController())
}