package com.scolotin.popularfilms.presentation.film

import com.scolotin.popularfilms.model.Film
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface FilmPresenterFactory {

    fun create(@Assisted("film") film: Film?): FilmPresenter

}
