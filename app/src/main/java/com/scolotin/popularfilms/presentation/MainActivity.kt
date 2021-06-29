package com.scolotin.popularfilms.presentation

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.scolotin.popularfilms.R
import com.scolotin.popularfilms.presentation.abs.AbsActivity
import com.scolotin.popularfilms.presentation.films.FilmsScreen
import javax.inject.Inject

class MainActivity : AbsActivity(R.layout.activity_main) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: router.newRootScreen(FilmsScreen.createFragment())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}
