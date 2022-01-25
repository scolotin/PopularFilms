package com.scolotin.popularfilms.presentation.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.scolotin.popularfilms.databinding.ItemFragmentFilmsBinding
import com.scolotin.popularfilms.model.Film

class FilmsAdapter(
    private val delegate: Delegate?
) : PagingDataAdapter<Film, FilmsViewHolder>(COMPARATOR) {

    interface Delegate {

        fun onPicked(film: Film)

    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, delegate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder =
        FilmsViewHolder(
            ItemFragmentFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }
}
