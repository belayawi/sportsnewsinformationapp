package com.amenawi.sportnewsinformationapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amenawi.sportnewsinformationapp.R
import com.amenawi.sportnewsinformationapp.models.HistoricalArchive

class HistoricalArchiveAdapter(private val archivesList: List<HistoricalArchive>) :
    RecyclerView.Adapter<HistoricalArchiveAdapter.ArchiveViewHolder>() {

    class ArchiveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyNameTextView: TextView = view.findViewById(R.id.historyNameTextView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_historical_archive, parent, false)
        return ArchiveViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        val archive = archivesList[position]
        holder.historyNameTextView.text = archive.historyName
        holder.dateTextView.text = archive.date
        holder.descriptionTextView.text = archive.description
    }

    override fun getItemCount() = archivesList.size
}
