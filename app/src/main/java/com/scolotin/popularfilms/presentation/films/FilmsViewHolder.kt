package com.scolotin.popularfilms.presentation.films

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.scolotin.popularfilms.databinding.ItemFragmentFilmsBinding
import com.scolotin.popularfilms.model.Film

class FilmsViewHolder(private val vb: ItemFragmentFilmsBinding) : RecyclerView.ViewHolder(vb.root) {

    fun bind(film: Film, delegate: FilmsAdapter.Delegate?) {
        with(vb) {
            filmTitle.text = film.title
            year.text = film.releaseDate
            rate.text = film.voteAverage.toString()
            val glideUrl = GlideUrl("https://image.tmdb.org/t/p/w780${film.posterPath}")
            Glide
                .with(vb.root)
                .load(glideUrl)
                .into(poster)
            root.setOnClickListener { delegate?.onPicked(film) }
        }
    }

}
