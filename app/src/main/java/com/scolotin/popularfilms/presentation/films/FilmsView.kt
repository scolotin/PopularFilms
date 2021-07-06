package com.scolotin.popularfilms.presentation.films

import com.scolotin.popularfilms.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface FilmsView : MvpView {

    @SingleState
    fun showFilms(films: List<Film>)

    @SingleState
    fun showError(message: String?)

}
