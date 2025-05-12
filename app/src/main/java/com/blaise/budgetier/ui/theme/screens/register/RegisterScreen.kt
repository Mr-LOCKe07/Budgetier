package com.blaise.budgetier.ui.theme.screens.register

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.blaise.budgetier.navigation.ROUTE_LOGIN
import com.blaise.budgetier.navigation.ROUTE_MAIN
import com.blaise.budgetier.ui.theme.MoneyGreen
import com.blaise.budgetier.ui.theme.NewOrange
import com.blaise.budgetier.ui.theme.YellowElegance
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Register_Screen(navController: NavHostController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    var fullname by remember { mutableStateOf(TextFieldValue("")) }
    var phonenumber by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmpassword by remember { mutableStateOf(TextFieldValue("")) }

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
                    contentDescription = "Person Icon",
                    tint = YellowElegance
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = "Full name",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = phonenumber,
            onValueChange = { phonenumber = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Call,
                    contentDescription = "Call Icon",
                    tint = YellowElegance
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = {
                Text(
                    text = "Phone number",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = YellowElegance
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = "Email",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Lock Icon",
                    tint = YellowElegance
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = "Password",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = confirmpassword,
            onValueChange = { confirmpassword = it },
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = "Lock Icon",
                    tint = YellowElegance
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {
                Text(
                    text = "Confirm Password",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            if (password.text == confirmpassword.text) {
                auth.createUserWithEmailAndPassword(email.text, password.text)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val uid = auth.currentUser?.uid ?: return@addOnCompleteListener
                            val userMap = mapOf(
                                "full_name" to fullname.text,
                                "phone_number" to phonenumber.text,
                                "email" to email.text
                            )

                            database.child("users").child(uid).setValue(userMap).addOnSuccessListener {
                                val sharedPref = context.getSharedPreferences("user_profile", Context.MODE_PRIVATE)
                                sharedPref.edit().apply {
                                    putString("full_name", fullname.text)
                                    putString("phone_number", phonenumber.text)
                                    putString("email", email.text)
                                    apply()
                                }
                                navController.navigate(ROUTE_MAIN)
                            }
                        } else {
                            Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        },
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

        Row{
            Text(
                text = "Already have an account?",
                color = NewOrange,
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif
            )

            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Login",
                    modifier = Modifier.clickable { navController.navigate(ROUTE_LOGIN) },
                    color = Color.White,
                    fontSize = 25.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Preview
@Composable
private fun RegisterPreview() {
    Register_Screen(rememberNavController())
}