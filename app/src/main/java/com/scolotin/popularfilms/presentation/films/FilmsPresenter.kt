package com.scolotin.popularfilms.presentation.films

import com.github.terrakok.cicerone.Router
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.film.FilmScreen
import com.scolotin.popularfilms.repository.FilmsRepository
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class FilmsPresenter @AssistedInject constructor(
        private val filmsRepository: FilmsRepository,
        private val router: Router
) : MvpPresenter<FilmsView>() {

    private var disposable: Disposable? = null

    override fun onFirstViewAttach() {

        disposable =
            filmsRepository
                .fetchPopularFilms()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(::showFilms)
                .subscribeOn(Schedulers.io())
                .subscribe()

    }

    private fun showFilms(films: List<Film>) {
        viewState.showFilms(films)
    }

    override fun onDestroy() {
        super.onDestroy()

        disposable?.dispose()
    }

    fun displayFilm(film: Film) {
        router.navigateTo(FilmScreen.createFragment(film))
    }

}
