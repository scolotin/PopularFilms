package com.scolotin.popularfilms.di.modules

import com.scolotin.popularfilms.di.IoScheduler
import com.scolotin.popularfilms.di.UiScheduler
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
class SchedulersModule {

    @Provides
    @UiScheduler
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @IoScheduler
    fun provideIoScheduler(): Scheduler = Schedulers.io()

}
