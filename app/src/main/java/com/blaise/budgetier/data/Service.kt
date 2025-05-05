package com.blaise.budgetier.data

import androidx.compose.ui.graphics.vector.ImageVector

data class Service (
    val name: String,
    val route: String,
    val icon: ImageVector,
    val budget: Double = 0.0,
    val categories: MutableList<Category> = mutableListOf()
    )