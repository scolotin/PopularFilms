package com.scolotin.popularfilms.presentation.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scolotin.popularfilms.databinding.ItemFragmentFilmsBinding
import com.scolotin.popularfilms.model.Film

class FilmsAdapter : RecyclerView.Adapter<FilmsViewHolder>(){

    private var films: List<Film> = listOf()

    fun submitList(films: List<Film>) {
        this.films = films
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder =
        FilmsViewHolder(
            ItemFragmentFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) =
        holder.bind(films[position])

    override fun getItemCount(): Int = films.size

}
