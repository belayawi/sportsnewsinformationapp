package com.amenawi.sportnewsinformationapp.fragments

import com.amenawi.sportnewsinformationapp.adapters.SportsAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amenawi.sportnewsinformationapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.amenawi.sportnewsinformationapp.models.SportCategory


class SportsFragment : Fragment() {

    private lateinit var sportsRecyclerView: RecyclerView
    private lateinit var sportsAdapter: SportsAdapter
    private val sportsList = ArrayList<SportCategory>()

    init {
        sportsList.apply {
            add(SportCategory(
                "Measure",
                "100m Sprint",
                "Maximize explosive power off the blocks focusing on speed and strength. Analyze form for efficiency. Prioritize nutrition and rest for recovery. Balance training with sprint intervals and flexibility exercises."
            ))
            add(SportCategory(
                "Precision",
                "Archery",
                "Master stance and grip for stability. Use breathing to maintain focus. Practice under various conditions to understand environmental effects. Utilize mental rehearsal for accuracy improvement."
            ))
            add(SportCategory(
                "Spectacle",
                "Gymnastics",
                "Combine technical skill with artistic expression through rigorous practice. Develop strength and flexibility for complex routines. Pay attention to movement precision and music rhythm. Artistry is key in captivating performances."
            ))
            add(SportCategory(
                "Combat",
                "Boxing",
                "Blend defensive strategy with punching technique. Anticipate and counter moves effectively. Enhance stamina and resilience through cardiovascular conditioning. Mental toughness prepares for competition challenges."
            ))
            add(SportCategory(
                "Play",
                "Soccer",
                "Synchronize individual skill with team tactics. Develop technical skills like dribbling and passing. Communication and spatial awareness are crucial on the field. Review game footage for insights and improvement."
            ))
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sports, container, false)
        sportsRecyclerView = view.findViewById(R.id.sportsRecyclerView)
        sportsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // Assuming a grid layout
        sportsAdapter = SportsAdapter(sportsList)
        sportsRecyclerView.adapter = sportsAdapter
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sportsRecyclerView = view.findViewById(R.id.sportsRecyclerView)
        sportsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        sportsRecyclerView.adapter = sportsAdapter

        val addFab: FloatingActionButton = view.findViewById(R.id.addSportFab)
        addFab.setOnClickListener {
            showAddSportDialog()
        }
    }
    @SuppressLint( "NotifyDataSetChanged","CutPasteId")
    private fun showAddSportDialog(){
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_sport, null)
        val spinner: Spinner = dialogView.findViewById(R.id.sportTypeSpinner)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sport_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        val sportNameInput = dialogView.findViewById<EditText>(R.id.sportNameInput)
        val sportInstructionInput = dialogView.findViewById<EditText>(R.id.sportInstructionInput)

        AlertDialog.Builder(requireContext()).setTitle("Add Sports")
            .setView(dialogView)
            .setPositiveButton("Add"){
                dialog, which ->
                val sportType = spinner.selectedItem.toString()
                val sportName = sportNameInput.text.toString()
                val instruction = sportInstructionInput.text.toString()
                if(validateInputs(sportType, sportName, instruction)){
                    val newCategory = SportCategory(sportType, sportName, instruction)
                    sportsList.add(newCategory)
                    sportsAdapter.notifyDataSetChanged()
                }

            }.setNegativeButton("Cancel", null)
            .show()

    }
    private fun validateInputs(sportType:String, sportName:String, instruction:String):Boolean {

        return when{
            sportType.isEmpty()->{
                Toast.makeText(context, "Please Enter Sport Type", Toast.LENGTH_SHORT).show()
                false
            }
            sportName.isEmpty()->{
                Toast.makeText(context, "Please Enter Sport Name", Toast.LENGTH_SHORT).show()
                false
            }
            instruction.isEmpty()->{
                Toast.makeText(context, "Please Enter Sport Instructions", Toast.LENGTH_SHORT).show()
                false
            }
            else-> true
        }
    }

}