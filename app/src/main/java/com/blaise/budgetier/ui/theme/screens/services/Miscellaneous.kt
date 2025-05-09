package com.blaise.budgetier.ui.theme.screens.services

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun Miscellaneous_Screen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var isEditing by remember { mutableStateOf(false) }
    var budgetLimit by remember { mutableStateOf("") }
    var savedLimit by remember { mutableStateOf("") }

    var gifts by remember { mutableStateOf("") }
    var donations by remember { mutableStateOf("") }
    var unexpected_expenses by remember { mutableStateOf("") }

    val totalSpent = listOf(gifts, donations, unexpected_expenses)
        .mapNotNull { it.toDoubleOrNull() }
        .sum()

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
            Text("Miscellaneous Budget",
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
                    label = { Text("Enter Budget Limit (KES)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        savedLimit = budgetLimit
                        isEditing = false
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
                value = gifts,
                onValueChange = { gifts = it },
                label = { Text("Gifts (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = donations,
                onValueChange = { donations = it },
                label = { Text("Donations (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = unexpected_expenses,
                onValueChange = { unexpected_expenses = it },
                label = { Text("Unexpected Expenses (KES)",
                    color = NewOrange,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif) },
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(16.dp))

            Text("Total Spent: KES $totalSpent", fontWeight = FontWeight.Bold, fontSize = 18.sp)

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
private fun MiscellaneousPreview() {
    Miscellaneous_Screen(rememberNavController())
}