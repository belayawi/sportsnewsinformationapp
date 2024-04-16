package com.amenawi.sportnewsinformationapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amenawi.sportnewsinformationapp.R
import com.amenawi.sportnewsinformationapp.models.Event

class EventsAdapter(private val eventsList: List<Event>) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventNameTextView: TextView = view.findViewById(R.id.eventNameTextView)
        val eventDescriptionTextView: TextView = view.findViewById(R.id.eventDescriptionTextView)
        val eventDateTextView: TextView = view.findViewById(R.id.eventDateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventsList[position]
        holder.eventNameTextView.text = event.name
        holder.eventDescriptionTextView.text = event.description
        holder.eventDateTextView.text = event.date
    }

    override fun getItemCount() = eventsList.size
}
