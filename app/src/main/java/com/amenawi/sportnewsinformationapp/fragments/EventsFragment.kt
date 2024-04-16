package com.amenawi.sportnewsinformationapp.fragments

import com.amenawi.sportnewsinformationapp.adapters.EventsAdapter
import android.annotation.SuppressLint
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
import com.amenawi.sportnewsinformationapp.models.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


class EventsFragment :  Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var eventsAdapter: EventsAdapter? = null
    private val eventList = ArrayList<Event>()

    init {
        eventList.apply {
            add(Event("International Chess Festival", "A gathering of the world's top chess players.", "2023-07-12"))
            add(Event("Marathon Grand Championship", "The ultimate test of endurance.", "2023-09-05"))
            add(Event("World Swimming Championships", "Featuring the best swimmers from around the globe.", "2023-10-21"))
            add(Event("Global Tech Expo", "Showcasing cutting-edge technology.", "2023-11-02"))
            add(Event("Mountain Biking Xtreme", "A thrilling downhill race.", "2024-03-15"))
            add(Event("Sailing World Cup", "A prestigious sailing competition.", "2024-04-20"))
            add(Event("International Yoga Day", "Celebrating the spirit of Yoga worldwide.", "2024-06-21"))
            add(Event("World Skateboarding Championships", "Top skateboarders showcasing their skills.", "2024-07-30"))
            add(Event("Ultimate Frisbee Championship", "Teams competing for the ultimate title.", "2024-08-14"))
            add(Event("Global Music Festival", "A celebration of music from around the world.", "2024-09-25"))
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.eventsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        eventsAdapter = EventsAdapter(eventList)
        recyclerView.adapter = eventsAdapter

        val addEventFab: FloatingActionButton = view.findViewById(R.id.addEventFab)
        addEventFab.setOnClickListener {
            showAddEventDialog()
        }
    }

    @SuppressLint("NewApi", "MissingInflatedId")
    private fun showAddEventDialog() {
        val layoutInflater = LayoutInflater.from(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_event, null)

        val eventNameInput = dialogView.findViewById<EditText>(R.id.eventNameInput)
        val eventDescriptionInput = dialogView.findViewById<EditText>(R.id.eventDescriptionInput)
        val eventDateInput = dialogView.findViewById<EditText>(R.id.eventDateInput)

        eventDateInput.isFocusable = false
        eventDateInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                eventDateInput.setText(selectedDate)
            }, year, month, day)
            datePickerDialog.show()
        }

        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Event")
            .setPositiveButton("Add") { _, _ ->
                val name = eventNameInput.text.toString()
                val description = eventDescriptionInput.text.toString()
                val date = eventDateInput.text.toString()
                val newEvent = Event(name, description, date)
                eventList.add(newEvent)
                eventsAdapter?.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun getSampleEvents(): List<Event> {
        return eventList;
    }

}