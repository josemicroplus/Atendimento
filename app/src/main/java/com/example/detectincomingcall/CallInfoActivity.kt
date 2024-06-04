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
import androidx.compose.ui.unit.dp
import com.microplus.wsGestplus.apis.GetContactsApi
import com.microplus.wsGestplus.models.Contact
import com.microplus.wsGestplus.models.ParamGetContacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CallInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val incomingNumber = intent.getStringExtra("INCOMING_NUMBER")
        setContent {
            CallInfoScreen(incomingNumber)
        }
    }
}

@Composable
fun CallInfoScreen(incomingNumber: String?) {
    var contactName by remember { mutableStateOf("") }
    var contactCompany by remember { mutableStateOf("") }
    var contactNIF by remember { mutableStateOf("") }

    LaunchedEffect(incomingNumber) {
        if (incomingNumber != null) {
            val contactInfo = fetchContactInfo(incomingNumber)
            if(contactInfo==null){
                contactName=""
                contactCompany=""
                contactNIF=""
            }else{
                contactName = contactInfo!!.givenName.toString()
                contactCompany = contactInfo!!.displayName.toString()
                contactNIF = contactInfo!!.familyName.toString()
            }
        }
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

suspend fun fetchContactInfo(phoneNumber: String): Contact? {
    val contactApi = GetContactsApi("https://core.api.gestplus.pt/")
    val paramGetContacts = ParamGetContacts(phoneNumber = phoneNumber)
    var objRet=contactApi.getContacts(paramGetContacts).objContactos;
    if(objRet!!.count()==0){
        return null;
    }else{
        return objRet[0];
    }
}
