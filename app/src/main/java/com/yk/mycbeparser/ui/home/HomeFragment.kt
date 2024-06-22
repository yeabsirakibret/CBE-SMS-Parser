package com.yk.mycbeparser.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yk.mycbeparser.SmsParser
import com.yk.mycbeparser.databinding.FragmentHomeBinding
import com.yk.mycbeparser.models.BankTransaction

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var tag: String = "home_fragment_log"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Call parseCbeSmsInbox function to retrieve BankTransaction array
        val transactionsArray = context?.let { SmsParser().parseCbeSmsInbox(it) }

        // Convert array to list and set the adapter
        transactionsArray?.let { transactions ->
            val transactionsList = transactions.toList()
            val adapter = TransactionAdapter(transactionsList)
            recyclerView.adapter = adapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
