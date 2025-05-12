package com.blaise.budgetier.ui.theme.screens.services

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.navigation.BudgetNavigationDrawer
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.NewOrange
import com.blaise.budgetier.ui.theme.YellowElegance
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blaise.budgetier.model.SharedServiceViewModel

@SuppressLint("CommitPrefEdits")
@Composable
fun Transportation_Screen(
    navController: NavHostController,
    viewModel: SharedServiceViewModel = viewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var budgetLimit by remember { mutableStateOf("") }
    var savedLimit by remember { mutableStateOf("") }

    var car_payment by remember { mutableStateOf("") }
    var fuel by remember { mutableStateOf("") }
    var maintenance by remember { mutableStateOf("") }
    var public_transit by remember { mutableStateOf("") }
    var ride_sharing by remember { mutableStateOf("") }
    var parking by remember { mutableStateOf("") }

    val totalSpent = listOf(car_payment, fuel, maintenance, public_transit, ride_sharing, parking)
        .mapNotNull { it.toDoubleOrNull() }
        .sum()
    viewModel.updateBudget("Transportation", totalSpent)
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("transportation_data", Context.MODE_PRIVATE)
    val countdownText = viewModel.countdownText
    LaunchedEffect(Unit) {
        viewModel.startCountdown(context, "transportation_last_input")
    }

    LaunchedEffect(Unit) {
        savedLimit = sharedPref.getString("budget_limit", "") ?: ""
        car_payment = sharedPref.getString("car_payment", "") ?: ""
        fuel = sharedPref.getString("fuel", "") ?: ""
        maintenance = sharedPref.getString("maintenance", "") ?: ""
        public_transit = sharedPref.getString("public_transit", "") ?: ""
        ride_sharing = sharedPref.getString("ride_sharing", "") ?: ""
        parking = sharedPref.getString("parking", "") ?: ""
    }

    LaunchedEffect(totalSpent) {
        viewModel.updateServiceBudget("Transportation", totalSpent)
    }

    Scaffold (
        bottomBar = { BudgetNavigationDrawer(navController) }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(YellowElegance)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Transportation Budget",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = NewOrange)

            Spacer(modifier = Modifier.height(12.dp))

            Box {
                Button(onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(2.dp, MoneyGreen)) {
                    Text("Menu")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Add Limit") },
                        onClick = {
                            isEditing = true
                            budgetLimit = ""
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Edit Limit") },
                        onClick = {
                            isEditing = true
                            budgetLimit = savedLimit
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete Limit") },
                        onClick = {
                            savedLimit = ""
                            isEditing = false
                            sharedPref.edit() {
                                remove("budget_limit")
                                apply()
                            }
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isEditing) {
                OutlinedTextField(
                    value = budgetLimit,
                    onValueChange = { budgetLimit = it },
                    label = { Text("Enter Budget Limit (KES)",
                        color = MoneyGreen) },
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        savedLimit = budgetLimit
                        isEditing = false
                        sharedPref.edit() {
                            putString("budget_limit", budgetLimit)
                            apply()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    border = BorderStroke(2.dp, MoneyGreen)
                ) {
                    Text("Save Limit")
                }
            } else if (savedLimit.isNotEmpty()) {
                Text("Budget Limit: KES $savedLimit", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = car_payment,
                onValueChange = {
                    car_payment = it
                    sharedPref.edit() {
                        putString("car_payment", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "transportation_last_input")
                },
                label = { Text("Car Payment (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = fuel,
                onValueChange = {
                    fuel = it
                    sharedPref.edit() {
                        putString("fuel", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "transportation_last_input")
                },
                label = { Text("Fuel (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = maintenance,
                onValueChange = {
                    maintenance = it
                    sharedPref.edit() {
                        putString("maintenance", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "transportation_last_input")
                },
                label = { Text("Maintenance (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = public_transit,
                onValueChange = {
                    public_transit = it
                    sharedPref.edit() {
                        putString("public_transit", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "transportation_last_input")
                },
                label = { Text("Public Transit (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                ) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = ride_sharing,
                onValueChange = {
                    ride_sharing = it
                    sharedPref.edit() {
                        putString("ride_sharing", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "transportation_last_input")
                },
                label = { Text("Ride Sharing (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                ) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = parking,
                onValueChange = {
                    parking = it
                    sharedPref.edit() {
                        putString("parking", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "transportation_last_input")
                },
                label = { Text("Parking (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                ) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

            Text("Total Spent: KES $totalSpent", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(countdownText.value, fontSize = 16.sp, color = Color.DarkGray)

            if (savedLimit.isNotEmpty()) {
                val limitValue = savedLimit.toDoubleOrNull()
                if (limitValue != null) {
                    val remaining = limitValue - totalSpent
                    Text(
                        text = if (remaining >= 0) "Remaining: KES $remaining" else "Over Budget by: KES ${-remaining}",
                        color = if (remaining >= 0) Color.Green else Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun AutomobilePreview() {
    Transportation_Screen(rememberNavController())
}