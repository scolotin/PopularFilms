package com.scolotin.popularfilms.presentation.film

import com.scolotin.popularfilms.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface FilmView : MvpView {

    @SingleState
    fun showFilm(film: Film)

}
