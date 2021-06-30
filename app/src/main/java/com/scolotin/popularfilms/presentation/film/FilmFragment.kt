package com.scolotin.popularfilms.presentation.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.scolotin.popularfilms.R
import com.scolotin.popularfilms.databinding.FragmentFilmBinding
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.abs.AbsFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FilmFragment : AbsFragment(R.layout.fragment_film), FilmView {

    companion object {

        private const val ARG_FILM = "film"

        fun newInstance(film: Film): Fragment = FilmFragment().apply {
            arguments = bundleOf(ARG_FILM to film)
        }

    }

    @Inject
    lateinit var filmPresenterFactory: FilmPresenterFactory

    private val film by lazy {
        arguments?.getParcelable<Film>(ARG_FILM)
    }

    private val presenter: FilmPresenter by moxyPresenter {
        filmPresenterFactory.create(film)
    }

    private var vb: FragmentFilmBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentFilmBinding
            .inflate(inflater, container, false)
            .apply {
                vb = this
            }
            .root

    override fun onDestroyView() {
        super.onDestroyView()

        vb = null
    }

    override fun showFilm(film: Film) {
        vb?.let {
            it.filmTitle.text = film.title
            it.metadata.text = film.releaseDate
            it.genre.text = film.genre
            it.rate.text = film.voteAverage.toString()
            it.description.text = film.overview
        }
    }

}
