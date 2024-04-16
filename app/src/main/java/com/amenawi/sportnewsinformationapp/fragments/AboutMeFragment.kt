package com.amenawi.sportnewsinformationapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.amenawi.sportnewsinformationapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AboutMeFragment : Fragment() {
    private lateinit var favoriteSportTextView: TextView
    private lateinit var fanClubTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        favoriteSportTextView = view.findViewById(R.id.favoriteSportTextView)
        fanClubTextView = view.findViewById(R.id.fanClubTextView)
        val fab = view.findViewById<FloatingActionButton>(R.id.addAboutMeFab)

        fab.setOnClickListener {
            showAddFavoriteDialog()
        }

        return view
    }
    private fun showAddFavoriteDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_favorite, null)

        val favoriteSportInput = dialogView.findViewById<EditText>(R.id.favoriteSport)
        val fanClubInput = dialogView.findViewById<EditText>(R.id.fanClub)

        builder.setView(dialogView)
            .setPositiveButton("Add") { dialog, id ->
                favoriteSportTextView.text = "Favorite Sport: ${favoriteSportInput.text}"
                fanClubTextView.text = "Fan Club: ${fanClubInput.text}"
            }
            .setNegativeButton("Cancel") { dialog, id ->
                dialog.dismiss()
            }

        builder.create().show()
    }

}