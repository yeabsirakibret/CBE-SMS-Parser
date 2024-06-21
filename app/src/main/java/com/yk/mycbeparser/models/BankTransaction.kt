package com.yk.mycbeparser.models

class BankTransaction {
    var id: Long? = null
    var date: String? = null
    var time: String? = null
    var timestamp: Long? = null
    var debitOrCredit: String? = null
    var amount: Double? = null
    var remainingBalance: Double? = null
}