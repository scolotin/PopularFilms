package com.scolotin.popularfilms.di.modules

import com.scolotin.popularfilms.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
