package com.yk.mycbeparser.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yk.mycbeparser.SmsParser
import com.yk.mycbeparser.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var tag:String = "home_fragment_log"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        this.context?.let {
            SmsParser().parseCbeSmsInbox(it)

            // Call parseCbeSmsInbox function to retrieve BankTransaction array

            // Loop through transactions and log each transaction
            context?.let { it1 -> SmsParser().parseCbeSmsInbox(it1) }
                ?.forEachIndexed { index, transaction ->
                    Log.d(tag, "Transaction $index:")
                    Log.d(tag, "ID: ${transaction.id}")
                    Log.d(tag, "Date: ${transaction.date}")
                    Log.d(tag, "Time: ${transaction.time}")
                    Log.d(tag, "Timestamp: ${transaction.timestamp}")
                    Log.d(tag, "Debit or Credit: ${transaction.debitOrCredit}")
                    Log.d(tag, "Amount: ${transaction.amount}")
                    Log.d(tag, "Remaining Balance: ${transaction.remainingBalance}")
                    Log.d(tag, "---------------------")
                }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}