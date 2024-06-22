package com.yk.mycbeparser.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yk.mycbeparser.databinding.ItemTransactionBinding
import com.yk.mycbeparser.models.BankTransaction

class TransactionAdapter(private val transactions: List<BankTransaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    class ViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: BankTransaction) {
            binding.apply {
                textViewId.text = "ID: ${transaction.id}"
                bankNameTextView.text = "Bank Name: CBE"
                textViewDate.text = "Date: ${transaction.date}"
                textViewTime.text = "Time: ${transaction.time}"
                textViewDebitCredit.text = "Debit/Credit: ${transaction.debitOrCredit}"
                textViewAmount.text = "Amount: ${transaction.amount}"
                textViewRemainingBalance.text = "Remaining Balance: ${transaction.remainingBalance}"
            }
        }
    }
}
