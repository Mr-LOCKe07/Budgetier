package com.blaise.budgetier.ui.theme.screens.addition

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddServiceDialog(
    onDismiss1: NavHostController,
    onDismiss: () -> Unit,
    onAddService: (String, ImageVector, Double) -> Unit
) {
    val serviceOptions = listOf("Groceries", "Automobile", "Residence", )
}

@Preview
@Composable
private fun AddPreview() {
    AddServiceDialog(
        rememberNavController(),
        onDismiss = {},
        onAddService = { _, _, _ ->}
    )
}
