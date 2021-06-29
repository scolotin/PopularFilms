package com.scolotin.popularfilms.di.modules

import com.scolotin.popularfilms.presentation.films.FilmsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @ContributesAndroidInjector
    abstract fun bindFilmsFragment(): FilmsFragment

}
