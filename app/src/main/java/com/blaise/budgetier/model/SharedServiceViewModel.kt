package com.blaise.budgetier.model

import androidx.compose.runtime.*
import android.content.Context
import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blaise.budgetier.ui.theme.screens.main.ServiceItem
import androidx.core.content.edit

class SharedServiceViewModel : ViewModel() {
    private val _services = mutableStateListOf<ServiceItem>()
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
}