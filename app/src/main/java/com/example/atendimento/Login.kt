package com.example.atendimento

import android.content.IntentFilter
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.microplus.wsGestplus.apis.LoginApi
import com.microplus.wsGestplus.models.LoginData
import android.util.Log

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iniciarBroadcastReceiver()
        setContent {
            LoginScreen()
        }
    }

    private fun iniciarBroadcastReceiver() {
        val filtro = IntentFilter()
        filtro.addAction("android.intent.action.PHONE_STATE")
        registerReceiver(CallReceiver(), filtro)
    }
}


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var idCompany by remember { mutableStateOf("") }
    var contribuinte by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )
        TextField(
            value = idCompany,
            onValueChange = { idCompany = it },
            label = { Text("ID da Empresa") }
        )
        TextField(
            value = contribuinte,
            onValueChange = { contribuinte = it },
            label = { Text("Contribuinte") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            Log.d("LoginScreen", "Tentando fazer login com email: $email")
            performLogin(email, password, idCompany, contribuinte,
                { msg ->
                    successMessage = msg
                    Log.d("LoginScreen", "Login bem-sucedido: $msg")


                  //  CallReceiver

                },
                { msg ->
                    errorMessage = msg
                    Log.e("LoginScreen", "Erro ao fazer login: $msg")
                }
            )
        }) {
            Text("Entrar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            Log.d("LoginScreen", "Recuperação de senha acionada para email: $email")
            recoverPassword(email)
        }) {
            Text("Esqueceu a Senha?")
        }
        if (errorMessage.isNotEmpty()) {
            Text(errorMessage, color = MaterialTheme.colors.error)
        }
        if (successMessage.isNotEmpty()) {
            Text(successMessage, color = MaterialTheme.colors.primary)
        }
    }
}

fun performLogin(
    email: String,
    password: String,
    idCompany: String,
    contribuinte: String,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    val logObj = LoginData(
        user = email,
        pass = password,
        origem = "Amb",
        idCompany = idCompany,
        database = "",
        iPorigem = "127.0.0.1",
        armazem = "12",
        contribuinte = contribuinte,
        licenseKey = "",
        userPosto = ""
    )
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    val objws = LoginApi("https://core.login.gestplus.pt/")
    try {
        val response = objws.getLogin(logObj)

        Log.d("performLogin", "Enviando dados de login: $logObj")

        if (response.status!!.isNotEmpty()) {
            onError("Erro ao fazer login. Verifique suas credenciais.")
        } else {
            onSuccess("Login realizado com sucesso.")
        }
    } catch (e: Exception) {
        Log.e("performLogin", "Erro de rede: ${e.message}")
        onError("Erro de rede. Tente novamente.")
    }
}

fun recoverPassword(email: String) {
    if (email.isEmpty()) {
        Log.e("recoverPassword", "Email está vazio.")
        return
    }
    Log.d("recoverPassword", "Enviando solicitação de recuperação de senha para: $email")
    // Placeholder para a lógica real de recuperação de senha
}
