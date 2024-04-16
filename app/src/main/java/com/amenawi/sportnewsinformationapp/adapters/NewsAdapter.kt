package com.amenawi.sportnewsinformationapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amenawi.sportnewsinformationapp.R
import com.bumptech.glide.Glide
import com.amenawi.sportnewsinformationapp.models.NewsItem

class NewsAdapter(private val newsItems: List<NewsItem>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.newsImageView)
        val titleTextView: TextView = view.findViewById(R.id.newsTitleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.newsDescriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsItems[position]
        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.description
        if (item.imageUrl.startsWith("drawable/")) {
            val imageName = item.imageUrl.split("/").last()
            val imageResId = holder.imageView.context.resources.getIdentifier(imageName, "drawable", holder.imageView.context.packageName)
            Glide.with(holder.imageView.context)
                .load(imageResId)
                .error(Glide.with(holder.imageView.context).load(R.drawable.hocky))
                .into(holder.imageView)
        } else {
            Glide.with(holder.imageView.context)
                .load(item.imageUrl)
                .into(holder.imageView)
        }
    }

    override fun getItemCount() = newsItems.size
}
