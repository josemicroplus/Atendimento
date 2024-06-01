package com.example.atendimento

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            if (state == TelephonyManager.EXTRA_STATE_RINGING && incomingNumber != null) {
                Log.d("CallReceiver", "Chamada recebida de: $incomingNumber")
                // Inicie a atividade para exibir as informações do número
                val showInfoIntent = Intent(context, CallInfoActivity::class.java).apply {
                    putExtra("INCOMING_NUMBER", incomingNumber)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(showInfoIntent)
            }
        }
    }
}
