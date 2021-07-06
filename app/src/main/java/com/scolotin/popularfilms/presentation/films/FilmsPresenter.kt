package com.scolotin.popularfilms.presentation.films

import com.github.terrakok.cicerone.Router
import com.scolotin.popularfilms.di.IoScheduler
import com.scolotin.popularfilms.di.UiScheduler
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.film.FilmScreen
import com.scolotin.popularfilms.repository.FilmsRepository
import com.scolotin.popularfilms.repository.connection.ConnectionState
import com.scolotin.popularfilms.repository.connection.ConnectionStateWatcher
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class FilmsPresenter @AssistedInject constructor(
        private val filmsRepository: FilmsRepository,
        private val connectionStateWatcher: ConnectionStateWatcher,
        private val router: Router,
        @UiScheduler private val uiScheduler: Scheduler,
        @IoScheduler private val ioScheduler: Scheduler
) : MvpPresenter<FilmsView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables.add(
            connectionStateWatcher
                .watchForConnectionState()
                .filter { connectionState -> connectionState == ConnectionState.CONNECTED }
                .observeOn(uiScheduler)
                .doOnNext { displayFilms() }
                .subscribeOn(ioScheduler)
                .subscribe()
        )

        displayFilms()
    }

    private fun displayFilms() {
        disposables.add(
            filmsRepository
                .fetchPopularFilms()
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler)
                .subscribe(
                    ::showFilms,
                    ::showError
                )
        )
    }

    private fun showFilms(films: List<Film>) {
        viewState.showFilms(films)
    }

    private fun showError(throwable: Throwable) {
        viewState.showError(throwable.message)
    }
    
    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

    fun displayFilm(film: Film) {
        router.navigateTo(FilmScreen.createFragment(film))
    }

}
