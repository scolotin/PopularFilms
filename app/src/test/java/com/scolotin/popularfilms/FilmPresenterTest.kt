package com.scolotin.popularfilms

import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.films.FilmsPresenter
import com.scolotin.popularfilms.presentation.films.`FilmsView$$State`
import com.scolotin.popularfilms.repository.FilmsRepository
import com.scolotin.popularfilms.repository.connection.ConnectionStateWatcher
import io.reactivex.rxjava3.core.Scheduler
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import org.mockito.MockitoAnnotations

class FilmsPresenterTest {

    private lateinit var filmsPresenter: FilmsPresenter

    @Mock
    private lateinit var filmsViewState: `FilmsView$$State`

    @Mock
    private lateinit var filmsRepository: FilmsRepository

    @Mock
    private lateinit var connectionStateWatcher: ConnectionStateWatcher

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var uiScheduler: Scheduler

    @Mock
    private lateinit var ioScheduler: Scheduler

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)

        filmsPresenter = FilmsPresenter(filmsRepository, connectionStateWatcher, router, uiScheduler, ioScheduler)
    }

    @Test
    fun showFilms_Test() {
        val films = listOf(Film(), Film(), Film())

        filmsViewState.showFilms(films)
        verify(filmsViewState, times(1)).showFilms(films)
    }

    @Test
    fun showError_Test() {
        val errorMessage = "error"

        filmsViewState.showError(errorMessage)
        verify(filmsViewState, times(1)).showError(any())
    }

    @Test
    fun createFragmentScreen_Test() {
        val film = Film()
        val fragmentScreen = filmsPresenter.createFragmentScreen(film)

        assertNotNull(fragmentScreen)
    }

    @Test
    fun navigateToFilmScreen_Test() {
        val film = Film()
        val fragmentScreen = filmsPresenter.createFragmentScreen(film)
        filmsPresenter.navigateToFilmScreen(fragmentScreen)

        verify(router, times(1)).navigateTo(fragmentScreen)
    }
}
