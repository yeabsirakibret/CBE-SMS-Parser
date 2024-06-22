package com.yk.mycbeparser.ui.graph

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.yk.mycbeparser.R
import com.yk.mycbeparser.SmsParser
import com.yk.mycbeparser.databinding.FragmentGraphBinding

class GraphFragment : Fragment() {
    private var _binding: FragmentGraphBinding? = null
    private val binding get() = _binding!!
    private val tag: String = "graph_fragment_log"

    private val GROUP_1_LABEL = "Max Remaining Balance of Each Month"
    private val BAR_WIDTH = 0.8f
    private var chart: BarChart? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        val root: View = binding.root

        chart = binding.barChart // Reference to the BarChart in your layout

        // Call parseCbeSmsInbox function to retrieve BankTransaction array
        val transactionsArray = context?.let { SmsParser().parseCbeSmsInbox(it) }

        // Prepare data for the chart
        if (transactionsArray != null) {


            val transactionsByMonth = mutableMapOf<String, Float>() // Map to store total amounts by month

            for (i in transactionsArray.indices) {
                var transaction = transactionsArray[i]

                val transactionMonth = transaction.date?.substring(3, 10) ?: continue // Extract YYYY-MM from date
                Log.i(tag, "${i}=>${transactionMonth}")
                val currentAmount = transactionsByMonth[transactionMonth] ?: 0f

                if(transaction.remainingBalance!!.toFloat() > currentAmount){
                    transactionsByMonth[transactionMonth] =  transaction.remainingBalance!!.toFloat()
                }


            }

            val entries = ArrayList<BarEntry>()
            var index = 0f

            // Convert transactionsByMonth map to BarEntry list
            for ((month, amount) in transactionsByMonth) {
                entries.add(BarEntry(index++, amount))
            }

            // Configure the BarChart
            chart!!.description.isEnabled = false
            chart?.apply {
                val barDataSet1 = BarDataSet(entries, GROUP_1_LABEL)
                barDataSet1.color = ContextCompat.getColor(requireContext(), R.color.teal_200)
                barDataSet1.setDrawValues(true) // Enable values inside bars if needed
                val barData = BarData(barDataSet1)
                barData.barWidth = BAR_WIDTH
                data = barData
                invalidate() // Refresh the chart
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
