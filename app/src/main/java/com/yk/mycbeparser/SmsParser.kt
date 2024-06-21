package com.yk.mycbeparser

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.yk.mycbeparser.models.BankTransaction
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SmsParser {
    private var tag:String = "sms_parser_log"

    fun parseCbeSmsInbox(context: Context): Array<BankTransaction> {

        val transactions = mutableListOf<BankTransaction>()

        try {
            val uri = Uri.parse("content://sms/inbox")
            val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)

            cursor?.use {
                while (it.moveToNext()) {
                    val sender = it.getString(it.getColumnIndexOrThrow("address"))
                    val messageBody = it.getString(it.getColumnIndexOrThrow("body"))

                    if (
                    //parse only from CBE and messages contain debited and credited
                        sender == "CBE" &&
                        (messageBody.toString().toLowerCase().contains("debited") || messageBody.toString().toLowerCase().contains("credited"))) {

                        val transaction = BankTransaction()
                        transaction.id = it.getLong(it.getColumnIndexOrThrow("_id"))
                        transaction.timestamp = it.getLong(it.getColumnIndexOrThrow("date"))

                        // Convert timestamp to human-readable date and time
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                        transaction.date = dateFormat.format(Date(transaction.timestamp!!))
                        transaction.time = timeFormat.format(Date(transaction.timestamp!!))

                        transaction.debitOrCredit = if (messageBody.toLowerCase().contains("debited")) "Debited" else "Credited"
                        // Assuming you have logic to extract amount and remaining balance from messageBody
                        transaction.amount = extractAmount(messageBody)
                        transaction.remainingBalance = extractRemainingBalance(messageBody)
                        transactions.add(transaction)

                        Log.d(tag, "Received SMS from CBE: $messageBody")

                    }
                }
            }

        }catch (e: Exception){
            Log.e(tag, e.message.toString())
        }

        return transactions.toTypedArray()
    }

    private fun extractAmount(messageBody: String): Double {
        // Implement your logic to extract amount from message body
        return 0.0 // Replace with actual logic
    }

    private fun extractRemainingBalance(messageBody: String): Double {
        // Implement your logic to extract remaining balance from message body
        return 0.0 // Replace with actual logic
    }

}