package com.blaise.budgetier.ui.theme.screens.addition

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddCategoryDialog(
    onDismiss: () -> Unit,
    onAddCategory: (String, String) -> Unit) {
    val categoryOptions = listOf("Fuel", "Insurance", "Servicing", "Repairs", "Groceries", "Rent", "Electricity Bill", "Water Bill")
    var selectedCategory by remember { mutableStateOf(categoryOptions.first()) }
    var budgetInput by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val isFormValid = selectedCategory.isNotBlank() && budgetInput.isNotBlank()

    AlertDialog(
        onDismissRequest =  onDismiss,
        confirmButton = {
            TextButton (onClick = {
               onAddCategory(selectedCategory, budgetInput)
                onDismiss()
            },
                enabled = isFormValid
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Budget Category") },
        text = {
            Column {
                Box {
                    OutlinedTextField(
                        value = selectedCategory,
                        onValueChange = {},
                        label = { Text("Select Category") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true }
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        categoryOptions.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    selectedCategory = category
                                    expanded = true
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = budgetInput,
                    onValueChange = { budgetInput = it },
                    label = { Text("Enter limit") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@Preview
@Composable
private fun AddPreview() {
    AddCategoryDialog(
        onDismiss = {},
        onAddCategory = { _, _ ->}
    )
}

