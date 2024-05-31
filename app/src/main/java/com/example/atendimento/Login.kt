package com.example.atendimento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.microplus.wsGestplus.models.LoginData
import com.microplus.wsGestplus.apis.LoginApi

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
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
            performLogin(email, password, idCompany, contribuinte, { msg -> successMessage = msg }, { msg -> errorMessage = msg })
        }) {
            Text("Entrar")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { recoverPassword(email) }) {
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
    val objws = LoginApi("https://core.login.gestplus.pt/")
    val response = objws.getLogin(logObj)

    if (response.status != "") {
        onError("Erro ao fazer login. Verifique suas credenciais.")
    } else {
        onSuccess("Login realizado com sucesso.")
    }
}

fun recoverPassword(email: String) {
    if (email.isEmpty()) {
        // Handle empty email case
        return
    }
    // Placeholder for actual password recovery logic
    // Example:
    // val response = api.sendRecoveryEmail(email)
    // if (response.isSuccessful) {
    //     // Handle success
    // } else {
    //     // Handle error
    // }
}
