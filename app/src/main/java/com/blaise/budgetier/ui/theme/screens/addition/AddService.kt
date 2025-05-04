package com.blaise.budgetier.ui.theme.screens.addition

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AddServiceDialog(
    onDismiss: () -> Unit,
    onAddService: (String, ImageVector, Double) -> Unit
) {
    val serviceOptions = listOf("Groceries", "Automobile", "Residence", )
}