package com.scolotin.popularfilms.presentation.films

import com.github.terrakok.cicerone.Router
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.film.FilmScreen
import com.scolotin.popularfilms.repository.FilmsRepository
import com.scolotin.popularfilms.repository.connection.ConnectionState
import com.scolotin.popularfilms.repository.connection.ConnectionStateWatcher
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable

import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class FilmsPresenter @AssistedInject constructor(
        private val filmsRepository: FilmsRepository,
        private val connectionStateWatcher: ConnectionStateWatcher,
        private val router: Router
) : MvpPresenter<FilmsView>() {

    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables.add(
            connectionStateWatcher
                .watchForConnectionState()
                .filter { connectionState -> connectionState == ConnectionState.CONNECTED }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { displayFilms() }
                .subscribeOn(Schedulers.io())
                .subscribe()
        )

        displayFilms()
    }

    private fun displayFilms() {
        disposables.add(
            filmsRepository
                .fetchPopularFilms()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
