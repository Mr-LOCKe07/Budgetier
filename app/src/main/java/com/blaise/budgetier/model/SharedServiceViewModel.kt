package com.blaise.budgetier.model

import androidx.compose.runtime.*
import android.content.Context
import android.os.CountDownTimer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.Commute
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.MiscellaneousServices
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blaise.budgetier.ui.theme.screens.main.ServiceItem
import androidx.core.content.edit
import com.blaise.budgetier.navigation.BottomNavItem
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

class SharedServiceViewModel : ViewModel() {

    private val _services = mutableStateListOf<ServiceItem>().apply {
        addAll(
            listOf(
                ServiceItem("Housing", Icons.Filled.Home, ROUTE_HOUSING),
                ServiceItem("Transport", Icons.Filled.Commute, ROUTE_TRANSPORTATION),
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
    val services: List<ServiceItem> = _services

    private val _countdownText = mutableStateOf("Loading countdown...")
    val countdownText: State<String> = _countdownText

    fun updateServiceBudget(name: String, budget: Double) {
        _services.replaceAll {
            if (it.name == name) it.copy(budget = budget, isActive = budget > 0.0) else it
        }
    }

    fun startCountdown(context: Context, key: String) {
        val sharedPref = context.getSharedPreferences("budget_data", Context.MODE_PRIVATE)
        val lastInputTime = sharedPref.getLong(key, 0L)
        val currentTime = System.currentTimeMillis()
        val nextInputTime = lastInputTime + 30L * 24 * 60 * 60 * 1000

        val remainingMillis = nextInputTime - currentTime
        if (remainingMillis > 0) {
            object : CountDownTimer(remainingMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val days = millisUntilFinished / (1000 * 60 * 60 * 24)
                    val hours = (millisUntilFinished / (1000 * 60 * 60)) % 24
                    val minutes = (millisUntilFinished / (1000 * 60)) % 60
                    val seconds = millisUntilFinished / (1000) % 60
                    _countdownText.value = "Next input in: ${days}d ${hours}h ${minutes}m ${seconds}s"
                }

                override fun onFinish() {
                    _countdownText.value = "You can now update your budget input."
                }
            }.start()
        } else {
            _countdownText.value = "You can now update your budget input."
        }
    }

    fun saveInputTime(context: Context, key: String) {
        val sharedPref = context.getSharedPreferences("budget_data", Context.MODE_PRIVATE)
        sharedPref.edit() {
            putLong(key, System.currentTimeMillis())
            apply()
        }
    }

    private val _serviceBudgets = mutableStateMapOf<String, Double>()
    val serviceBudgets: Map<String, Double> = _serviceBudgets

    fun updateBudget(serviceName: String, amount: Double) {
        _serviceBudgets[serviceName] = amount
    }

    fun getTotalBudget(): Double {
        return _serviceBudgets.values.sum()
    }

    fun getActiveServiceCount(): Int {
        return _serviceBudgets.values.count { it > 0.0 }
    }
}