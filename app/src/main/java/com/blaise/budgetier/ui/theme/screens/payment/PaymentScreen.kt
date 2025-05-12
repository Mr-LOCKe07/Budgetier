package com.blaise.budgetier.ui.theme.screens.payment

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

interface MpesaApi {
    @POST("mpesa/stkpush/v1/processrequest")
    fun initiateStkPush(
        @Header("Authorization") token: String,
        @Body body: StkPushRequest
    ): Call<StkPushResponse>
}

data class StkPushRequest(
    val BusinessShortCode: String,
    val Password: String,
    val Timestamp: String,
    val TransactionType: String = "CustomerPayBillOnline",
    val Amount: String,
    val PartyA: String,
    val PartyB: String,
    val PhoneNumber: String,
    val CallBackURL: String,
    val AccountReference: String,
    val TransactionDesc: String
)

data class StkPushResponse(
    val MerchantRequestID: String?,
    val CheckoutRequestID: String?,
    val ResponseCode: String?,
    val CustomerMessage: String?
)

fun getTimestamp(): String {
    val sdf = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
    return sdf.format(Date())
}

fun generatePassword(shortCode: String, passkey: String, timestamp: String): String {
    val dataToEncode = "$shortCode$passkey$timestamp"
    return Base64.encodeToString(dataToEncode.toByteArray(), Base64.NO_WRAP)
}

fun getAccessToken(consumerKey: String, consumerSecret: String, onTokenReady: (String) -> Unit) {
    val credentials = "$consumerKey:$consumerSecret"
    val basicAuth = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
        .header("Authorization", basicAuth)
        .build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            Log.e("TOKEN", "Error: ${e.localizedMessage}")
        }

        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
            response.body?.let {
                val json = JSONObject(it.string())
                val accessToken = json.getString("access_token")
                onTokenReady(accessToken)
            }
        }
    })
}

fun performPayment(
    context: Context,
    totalAmount: Double,
    accessToken: String,
    token: String
) {
    val sharedPref: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val phoneNumber = sharedPref.getString("phone_number", null) ?: run {
        Log.e("M-PESA", "Phone number not found in SharedPreferences")
        return
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://sandbox.safaricom.co.ke/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(MpesaApi::class.java)

    val timestamp = getTimestamp()
    val password = generatePassword("174379", "your_passkey_here", timestamp)

    val request = StkPushRequest(
        BusinessShortCode = "174379",
        Password = password,
        Timestamp = timestamp,
        Amount = totalAmount.toInt().toString(),
        PartyA = phoneNumber,
        PartyB = "174379",
        PhoneNumber = phoneNumber,
        CallBackURL = "https://yourdomain.com/callback",
        AccountReference = "Budgetier",
        TransactionDesc = "Budget Payment"
    )

    api.initiateStkPush("Bearer $accessToken", request)
        .enqueue(object : Callback<StkPushResponse> {
            override fun onResponse(
                call: Call<StkPushResponse>,
                response: Response<StkPushResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("M-PESA", "Success: ${response.body()?.CustomerMessage}")
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("M-PESA", "Failure: $errorBody")
                }
            }

            override fun onFailure(call: Call<StkPushResponse>, t: Throwable) {
                Log.e("M-PESA", "Error: ${t.localizedMessage}")
            }
        })
}

@Composable
fun Payment_Screen(totalAmount: Double, onPayClick: (Double) -> Unit, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("You're about to pay:", fontSize = 20.sp)
        Text("KES ${"%.2f".format(totalAmount)}", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onPayClick(totalAmount) }) {
            Text("Pay with M-Pesa")
        }
    }
}


