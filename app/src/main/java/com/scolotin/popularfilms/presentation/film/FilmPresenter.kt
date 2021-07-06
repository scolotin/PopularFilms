package com.scolotin.popularfilms.presentation.film

import com.scolotin.popularfilms.model.Film
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class FilmPresenter @AssistedInject constructor(
    @Assisted("film") private val film: Film?
) : MvpPresenter<FilmView>() {

    override fun onFirstViewAttach() {
        film?.let { showFilm(it) }
    }

    private fun showFilm(film: Film) {
        viewState.showFilm(film)
    }

}
