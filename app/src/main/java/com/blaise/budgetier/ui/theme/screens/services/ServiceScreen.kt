package com.blaise.budgetier.ui.theme.screens.services

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.blaise.budgetier.data.Category
import com.blaise.budgetier.model.SharedServiceViewModel
import com.blaise.budgetier.navigation.BottomBar
import com.blaise.budgetier.ui.theme.NewOrange

@Composable
fun Service_Screen(route: String, viewModel: SharedServiceViewModel) {
    val service = viewModel.getServiceByRoute(route)
    var showDialog by remember { mutableStateOf(false) }
    var newCategory by remember { mutableStateOf("") }
    var categoryBudget by remember { mutableStateOf("") }

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
        Column (modifier = Modifier.padding(paddingValues)){
            Text("${service?.name} Categories")

            service?.categories?.forEach { category ->
                Card (modifier = Modifier.fillMaxWidth().padding(8.dp)){
                    Row (modifier = Modifier.padding(16.dp)){
                        Text("${category.name}: KES ${category.budget}")
                    }
                }
            }

            Button(onClick = { showDialog = true }) {
                Text("Add Category")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false},
                    confirmButton = {
                        TextButton(onClick = {
                            val budget = categoryBudget.toDoubleOrNull()
                            if (newCategory.isNotBlank() && budget != null) {
                                viewModel.addCategoryToService(route, Category(newCategory, budget))
                                newCategory = ""
                                categoryBudget = ""
                                showDialog = false
                            }
                        }) { Text("Add")}
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) { Text("Cancel") }
                    },
                    title = { Text("Add Category") },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = newCategory,
                                onValueChange = { newCategory = it },
                                label = { Text("Category Name") }
                            )
                            OutlinedTextField(
                                value = categoryBudget,
                                onValueChange = { newCategory = it },
                                label = { Text("Category Budget") },
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                        }
                    }
                )
            }
        }
    }
}