package com.scolotin.popularfilms.presentation.films

import dagger.assisted.AssistedFactory

@AssistedFactory
interface FilmsPresenterFactory {

    fun create(): FilmsPresenter

}
