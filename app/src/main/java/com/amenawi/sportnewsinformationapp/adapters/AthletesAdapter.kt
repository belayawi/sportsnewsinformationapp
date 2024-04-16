package com.amenawi.sportnewsinformationapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amenawi.sportnewsinformationapp.R
import com.amenawi.sportnewsinformationapp.models.Athlete

class AthletesAdapter(private val athletes: List<Athlete>) : RecyclerView.Adapter<AthletesAdapter.AthleteViewHolder>() {

    class AthleteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.tvAthleteName)
        val sportView: TextView = view.findViewById(R.id.tvSportName)
        val countryView: TextView = view.findViewById(R.id.tvCountry)
        val bestPerformanceView:TextView = view.findViewById(R.id.tvBestPerformance)
        val medalsView: TextView = view.findViewById(R.id.tvMedals)
        val factsView:TextView = view.findViewById(R.id.tvFacts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthleteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_athlete, parent, false)
        return AthleteViewHolder(view)
    }

    override fun onBindViewHolder(holder: AthleteViewHolder, position: Int) {
        val athlete = athletes[position]
        holder.nameTextView.text = athlete.name
        holder.sportView.text = athlete.sportName
        holder.countryView.text = athlete.country
        holder.bestPerformanceView.text = athlete.bestPerformance
        holder.medalsView.text = athlete.medals
        holder.factsView.text= athlete.facts

    }

    override fun getItemCount() = athletes.size
}
