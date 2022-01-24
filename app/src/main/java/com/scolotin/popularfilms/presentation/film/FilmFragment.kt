package com.scolotin.popularfilms.presentation.film

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.scolotin.popularfilms.R
import com.scolotin.popularfilms.TMDB_IMAGE_BASE_URL
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

    private val film by lazy {
        arguments?.getParcelable(ARG_FILM) ?: Film(getString(R.string.txt_unknown_film))
    }

    @Inject
    lateinit var filmPresenterFactory: FilmPresenterFactory

    private val presenter: FilmPresenter by moxyPresenter {
        filmPresenterFactory.create(film)
    }

    private val vb: FragmentFilmBinding by viewBinding()

    override fun showFilm(film: Film) {
        with(vb) {
            val glideUrl = GlideUrl("$TMDB_IMAGE_BASE_URL${film.posterPath}")
            Glide.with(vb.root)
                .load(glideUrl)
                .into(poster)
            filmTitle.text = film.title
            metadata.text = film.releaseDate
            rate.text = film.voteAverage.toString()
            description.text = film.overview
        }
    }

}
