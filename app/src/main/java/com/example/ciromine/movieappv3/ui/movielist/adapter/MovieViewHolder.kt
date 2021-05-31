package com.example.ciromine.movieappv3.ui.movielist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.ciromine.movieappv3.databinding.ViewItemCharacterBinding
import com.example.ciromine.movieappv3.domain.model.DomainMovie

class MovieViewHolder(val binding: ViewItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(attrs: DomainMovie, onClickListener: (Int) -> Unit) {
        binding.apply {
            title.text = attrs.title
        }
    }
}