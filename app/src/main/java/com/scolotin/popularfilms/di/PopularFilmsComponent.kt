package com.scolotin.popularfilms.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.scolotin.popularfilms.PopularFilms
import com.scolotin.popularfilms.di.modules.FilmsModule
import com.scolotin.popularfilms.di.modules.MainModule
import com.scolotin.popularfilms.di.modules.NetworkModule
import com.scolotin.popularfilms.di.modules.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [ AndroidInjectionModule::class, MainModule::class, UiModule::class, FilmsModule::class, NetworkModule::class ])
interface PopularFilmsComponent : AndroidInjector<PopularFilms> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        fun build(): PopularFilmsComponent

    }
}
