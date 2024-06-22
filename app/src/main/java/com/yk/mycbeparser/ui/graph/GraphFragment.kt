package com.yk.mycbeparser.ui.graph

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.yk.mycbeparser.R
import com.yk.mycbeparser.SmsParser
import com.yk.mycbeparser.databinding.FragmentDashboardBinding
import com.yk.mycbeparser.databinding.FragmentGraphBinding
import com.yk.mycbeparser.ui.home.TransactionAdapter


class GraphFragment : Fragment() {
    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!
    private var tag: String = "graph_fragment_log"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Call parseCbeSmsInbox function to retrieve BankTransaction array
        val transactionsArray = context?.let { SmsParser().parseCbeSmsInbox(it) }

        if (transactionsArray != null) {
            for (transaction in transactionsArray) {
                Log.d(tag, "Transaction: ${transaction.id}")
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}