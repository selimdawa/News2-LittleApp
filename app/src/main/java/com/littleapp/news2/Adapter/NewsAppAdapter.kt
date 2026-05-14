package com.littleapp.news2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.littleapp.news2.Model.NewsHeadlines
import com.littleapp.news2.SelectListener
import com.littleapp.news2.Unit.VOID
import com.littleapp.news2.databinding.ItemNewsBinding

class NewsAppAdapter(
    private val context: Context, var headlines: List<NewsHeadlines?>?,
    private val listener: SelectListener,
) : RecyclerView.Adapter<NewsAppViewHolder>() {

    private var binding: ItemNewsBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAppViewHolder {
        binding = ItemNewsBinding.inflate(LayoutInflater.from(context), parent, false)
        return NewsAppViewHolder(binding!!.root)
    }

    override fun onBindViewHolder(holder: NewsAppViewHolder, position: Int) {
        val list = headlines!![position]
        val content = list!!.title
        val source = list.source!!.name
        val urlToImage = list.urlToImage

        if (content != null) holder.content.text = content
        if (source != null) holder.source.text = source
        if (urlToImage != null) VOID.Glide(context, urlToImage, holder.image)
        holder.card.setOnClickListener { listener.onNewsClicked(list) }
    }

    override fun getItemCount(): Int {
        return headlines!!.size
    }
}