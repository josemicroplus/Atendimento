package com.example.atendimento

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.atendimento.MainActivity
import com.microplus.wsGestplus.apis.GetContactsApi
import com.microplus.wsGestplus.apis.GetPhoneNumbersApi
import com.microplus.wsGestplus.models.Contact
import com.microplus.wsGestplus.models.ParamGetContacts
import com.microplus.wsGestplus.models.ParamGetPhoneNumbers


class CallInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CallInfoScreen()
        }
    }
}

@Composable
fun CallInfoScreen() {
    val intent = Intent(LocalContext.current,MainActivity::class.java)
    val incomingNumber =  intent.getStringExtra("INCOMING_NUMBER")
    var contactName by remember { mutableStateOf("") }
    var contactCompany by remember { mutableStateOf("") }
    var contactNIF by remember { mutableStateOf("") }

    LaunchedEffect(incomingNumber) {
        // Fetch contact info using API
        val contactInfo = fetchContactInfo(incomingNumber.toString())
        contactName = contactInfo.givenName.toString()
        contactCompany = contactInfo.displayName.toString()
        contactNIF = contactInfo.familyName.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Número de Telefone: $incomingNumber")
        Text("Nome: $contactName")
        Text("Empresa: $contactCompany")
        Text("NIF: $contactNIF")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Lógica para terminar a chamada
        }) {
            Text("Terminar Chamada")
        }
    }
}

suspend fun fetchContactInfo(phoneNumber: String): Contact {
    // Implementar a lógica para buscar as informações de contato usando a API com.microplus.wsGestplus
    val contactApi = GetContactsApi("https://core.api.gestplus.pt/")

    var obhPara:ParamGetContacts= ParamGetContacts(
        phoneNumber=phoneNumber
    )
    return contactApi.getContacts( obhPara ).objContactos!![0]
}
