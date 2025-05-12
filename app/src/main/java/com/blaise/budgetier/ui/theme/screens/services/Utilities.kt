package com.blaise.budgetier.ui.theme.screens.services

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
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.model.SharedServiceViewModel
import com.blaise.budgetier.navigation.BudgetNavigationDrawer
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.NewOrange
import com.blaise.budgetier.ui.theme.YellowElegance

@Composable
fun Utilities_Screen(
    navController: NavHostController,
    viewModel: SharedServiceViewModel = viewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var budgetLimit by remember { mutableStateOf("") }
    var savedLimit by remember { mutableStateOf("") }

    var electricity by remember { mutableStateOf("") }
    var water by remember { mutableStateOf("") }
    var gas by remember { mutableStateOf("") }
    var internet by remember { mutableStateOf("") }
    var smart_phone by remember { mutableStateOf("") }
    var trash_collection by remember { mutableStateOf("") }

    val totalSpent = listOf(electricity, water, gas, internet, smart_phone, trash_collection)
        .mapNotNull { it.toDoubleOrNull() }
        .sum()
    viewModel.updateBudget("Utilities", totalSpent)
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("housing_data", Context.MODE_PRIVATE)
    val countdownText = viewModel.countdownText
    LaunchedEffect(Unit) {
        viewModel.startCountdown(context, "transportation_last_input")
    }


    LaunchedEffect(Unit) {
        savedLimit = sharedPref.getString("budget_limit", "") ?: ""
        electricity = sharedPref.getString("electricity", "") ?: ""
        water = sharedPref.getString("water", "") ?: ""
        gas = sharedPref.getString("gas", "") ?: ""
        internet = sharedPref.getString("internet", "") ?: ""
        smart_phone = sharedPref.getString("smart_phone", "") ?: ""
        trash_collection = sharedPref.getString("trash_collection", "") ?: ""
    }

    LaunchedEffect(totalSpent) {
        viewModel.updateServiceBudget("Utilities", totalSpent)
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
            Text("Utilities Budget",
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
                        viewModel.saveInputTime(context, "utilities_last_input")
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
                value = electricity,
                onValueChange = {
                    electricity = it
                    sharedPref.edit() {
                        putString("electricity", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "utilities_last_input")
                },
                label = { Text("Electricity (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = water,
                onValueChange = {
                    water = it
                    sharedPref.edit() {
                        putString("water", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "utilities_last_input")
                },
                label = { Text("Water (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = gas,
                onValueChange = {
                    gas = it
                    sharedPref.edit() {
                        putString("gas", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "utilities_last_input")
                },
                label = { Text("Gas (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = internet,
                onValueChange = {
                    internet = it
                    sharedPref.edit() {
                        putString("internet", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "utilities_last_input")
                },
                label = { Text("Internet (KES)",
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
                value = smart_phone,
                onValueChange = {
                    smart_phone = it
                    sharedPref.edit() {
                        putString("smart_phone", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "utilities_last_input")
                },
                label = { Text("Smart Phone (KES)",
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
                value = trash_collection,
                onValueChange = {
                    trash_collection = it
                    sharedPref.edit() {
                        putString("trash_collection", it)
                        apply()
                    }
                    viewModel.saveInputTime(context, "utilities_last_input")
                },
                label = { Text("Trash Collection (KES)",
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
private fun UtilitiesPreview() {
    Utilities_Screen(rememberNavController())
}