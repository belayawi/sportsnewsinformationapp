package com.amenawi.sportnewsinformationapp.fragments

import com.amenawi.sportnewsinformationapp.adapters.NewsAdapter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amenawi.sportnewsinformationapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.amenawi.sportnewsinformationapp.models.NewsItem


class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private val newsItems = ArrayList<NewsItem>()

    init {
        newsItems.add(NewsItem(
            title = "Goal Glory: Football Champions Emerge!",
            description = "The local high school basketball team clinched the state championship after a thrilling final game that ended in overtime.",
            imageUrl = "drawable/football"
        ))
        newsItems.add(NewsItem(
            title = "Marathon Runner Sets New Record",
            description = "Jane Doe, an amateur runner from Smalltown, set a new record in the annual city marathon, beating the previous record set in 2015.",
            imageUrl = "drawable/runners"
        ))
        newsItems.add(NewsItem(
            title = "Hoops Heroes: Basketball Champions Triumph!",
            description = "Jane Doe, an amateur runner from Smalltown, set a new record in the annual city marathon, beating the previous record set in 2015.",
            imageUrl = "drawable/basket_ball"
        ))
        newsItems.add(NewsItem(
            title = "Aqua Triumph: The Swimming Champions' Saga",
            description = "Jane Doe, an amateur runner from Smalltown, set a new record in the annual city marathon, beating the previous record set in 2015.",
            imageUrl = "drawable/swimming"
        ))
        newsItems.add(NewsItem(
            title = "Volleyball Victors: Champions Crowned!",
            description = "The community pool at Central Park has reopened after several months of renovations, featuring new water slides and upgraded facilities.",
            imageUrl = "drawable/vallyball"
        ))
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        recyclerView = view.findViewById(R.id.newsRecyclerView) // Make sure you define this ID in your fragment's layout
        recyclerView.layoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(getNewsItems())
        recyclerView.adapter = newsAdapter

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.newsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = NewsAdapter(getNewsItems())
        newsAdapter = NewsAdapter(newsItems)
        recyclerView.adapter = newsAdapter

        view.findViewById<FloatingActionButton>(R.id.addNewsFab).setOnClickListener {
            showAddNewsDialog()
        }
    }
    private fun getNewsItems(): List<NewsItem> {

        return newsItems;
    }
    @SuppressLint("NotifyDataSetChanged", "DiscouragedApi")
    private fun showAddNewsDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_news, null)
        val titleInput = dialogView.findViewById<EditText>(R.id.titleInput)
        val descriptionInput = dialogView.findViewById<EditText>(R.id.descriptionInput)
        val imageUrlInput = dialogView.findViewById<EditText>(R.id.imageUrlInput)
        AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add News")
            .setPositiveButton("Add") {  dialog, which->
                val title = titleInput.text.toString()
                val description = descriptionInput.text.toString()
                val imageUrl = imageUrlInput.text.toString()
                val newItem = NewsItem(title, description,imageUrl)
                newsItems.add(newItem)
                Toast.makeText(requireContext(),imageUrl, Toast.LENGTH_LONG).show()
                newsAdapter.notifyDataSetChanged()
            }.setNegativeButton("Cancel", null)
            .show()
    }
}

