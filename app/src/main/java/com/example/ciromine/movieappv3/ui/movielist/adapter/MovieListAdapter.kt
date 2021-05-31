package com.example.ciromine.movieappv3.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ciromine.movieappv3.databinding.ViewItemCharacterBinding
import com.example.ciromine.movieappv3.domain.model.DomainMovie

class MovieListAdapter(
    private val items: List<DomainMovie>,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ViewItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val summary = items[position]
        (holder as MovieViewHolder).bind(summary, onItemClickListener)
    }
}