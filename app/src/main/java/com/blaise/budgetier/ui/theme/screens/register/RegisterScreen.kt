package com.blaise.budgetier.ui.theme.screens.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.navigation.ROUTE_HOME
import com.blaise.budgetier.navigation.ROUTE_REGISTER
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.YellowElegance

@Composable
fun Register_Screen(navController: NavHostController) {
    var fullname by remember { mutableStateOf(TextFieldValue("")) }
    var phonenumber by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            text = "Register",
            color = MoneyGreen,
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = fullname,
            onValueChange = { fullname = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Person Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = "Fill in your full name",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = phonenumber,
            onValueChange = { phonenumber = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Call,
                    contentDescription = "Call Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(
                    text = "Fill in your phone number",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = "Fill in your email",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Lock Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = "Fill in a password",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {navController.navigate(ROUTE_HOME)},
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            border = BorderStroke(2.dp, YellowElegance),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(300.dp)
        ) {
            Text(text = "Register",
                color = MoneyGreen,
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif)
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Already have an account?",
            color = Color.Red,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif)

        Text(text = "Click here to Login",
            modifier = Modifier.clickable { ROUTE_REGISTER },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive)
    }
}

@Preview
@Composable
private fun RegisterPreview() {
    Register_Screen(rememberNavController())
}