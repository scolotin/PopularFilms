package com.scolotin.popularfilms.presentation.film

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
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

    private val vb: FragmentFilmBinding by viewBinding()

    override fun showFilm(film: Film) {
        with(vb) {
            filmTitle.text = film.title
            metadata.text = film.releaseDate
            genre.text = film.genre
            rate.text = film.voteAverage.toString()
            description.text = film.overview
        }
    }

}
