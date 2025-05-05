package com.blaise.budgetier.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.blaise.budgetier.data.Category
import com.blaise.budgetier.data.Service

class SharedServiceViewModel : ViewModel() {
    private val _services = mutableStateListOf<Service>()
    val services: List<Service> = _services

    init {
        _services.addAll(
            listOf(
                Service("Residence", "residence", Icons.Filled.Home, 0.0),
                Service("Groceries", "groceries", Icons.Filled.ShoppingCart, 0.0),
                Service("Automobile", "automobile", Icons.Filled.DirectionsCar, 0.0),
                Service("Subscriptions", "subscriptions", Icons.Filled.CreditCard, 0.0),
            )
        )
    }
    fun addService(service: Service) {
            _services.add(service)
    }

    fun addCategoryToService(serviceRoute: String, category: Category) {
        _services.find { it.route == serviceRoute }?.categories?.add(category)
    }

    fun deleteCategory(serviceRoute: String, category: Category) {
        _services.find { it.route == serviceRoute }?.categories?.remove(category)
    }

    fun deleteService(serviceRoute: String) {
        _services.removeAll { it.route == serviceRoute }
    }

    fun getServiceByRoute(route: String): Service? {
        return _services.find{ it.route == route }
    }
}