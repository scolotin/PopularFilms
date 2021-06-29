package com.scolotin.popularfilms

import com.github.terrakok.cicerone.Cicerone
import com.scolotin.popularfilms.di.DaggerPopularFilmsComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PopularFilms : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<PopularFilms> =
        DaggerPopularFilmsComponent.builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .build()

}
