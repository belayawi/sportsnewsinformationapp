package com.amenawi.sportnewsinformationapp.fragments

import com.amenawi.sportnewsinformationapp.adapters.HistoricalArchiveAdapter
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amenawi.sportnewsinformationapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.amenawi.sportnewsinformationapp.models.HistoricalArchive
import java.util.Calendar


class HistoricalSportsArchiveFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoricalArchiveAdapter
    private val historicalArchives = ArrayList<HistoricalArchive>()

    init {
        historicalArchives.apply {
            add(
                HistoricalArchive(
                    historyName = "First Modern Olympics",
                    date = "1896",
                    description = "Held in Athens, Greece, marking the beginning of the Modern Olympics."
                )
            )
            add(
                HistoricalArchive(
                    historyName = "FIFA World Cup Inauguration",
                    date = "1930",
                    description = "The first ever FIFA World Cup took place in Uruguay."
                )
            )
            add(
                HistoricalArchive(
                    historyName = "Wilt Chamberlain's 100 Point Game",
                    date = "1962",
                    description = "Wilt Chamberlain set the single-game scoring record in the NBA by scoring 100 points."
                )
            )
            add(
                HistoricalArchive(
                    historyName = "Miracle on Ice",
                    date = "1980",
                    description = "The US Olympic hockey team defeated the Soviet Union in a surprising upset during the Winter Olympics."
                )
            )
            add(
                HistoricalArchive(
                    historyName = "Michael Phelps' 8 Gold Medals",
                    date = "2008",
                    description = "Michael Phelps won 8 gold medals at the Beijing Olympics, setting the record for most golds in a single Olympics."
                )
            )
            add(
                HistoricalArchive(
                    historyName = "Usain Bolt's 9.58s 100m",
                    date = "2009",
                    description = "Usain Bolt set the world record for the 100m sprint at the World Championships in Berlin."
                )
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historical_sports_archive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.archivesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HistoricalArchiveAdapter(historicalArchives)
        recyclerView.adapter = adapter
        historicalArchives.addAll(getSampleArchives())
        adapter.notifyDataSetChanged()

        view.findViewById<FloatingActionButton>(R.id.addHistoricalArchive).setOnClickListener {
            showAddHistoricalArchiveDialog()
        }
    }

    private fun getSampleArchives(): List<HistoricalArchive> {
        return historicalArchives;
    }

    private fun showAddHistoricalArchiveDialog() {
        val view =
            LayoutInflater.from(context).inflate(R.layout.dialog_add_historical_archive, null)
        val date = view.findViewById<EditText>(R.id.historyDateInput)
        date.isFocusable = false

        date.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, dayOfMonth ->
                val selectedDate =
                    String.format("%d-%02d-%02d", selectedYear, selectedMonth + 1, dayOfMonth)
                date.setText(selectedDate)
            }, year, month, day).show()
        }

        AlertDialog.Builder(requireContext()).apply {
            setView(view)
            setTitle("Add Historical Archive")
            setPositiveButton("Add") { _, _ ->
                val name = view.findViewById<EditText>(R.id.historyNameInput).text.toString()
                val description =
                    view.findViewById<EditText>(R.id.historyDescriptionInput).text.toString()
                val dateInput = date.text.toString()
                val newArchive = HistoricalArchive(name, dateInput, description)

                (recyclerView.adapter as? HistoricalArchiveAdapter)?.apply {
                    historicalArchives.add(newArchive)
                    notifyDataSetChanged()
                }
            }
            setNegativeButton("Cancel", null)
        }.show()

    }
}