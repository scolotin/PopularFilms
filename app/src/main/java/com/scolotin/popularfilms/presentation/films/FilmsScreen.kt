package com.scolotin.popularfilms.presentation.films

import com.github.terrakok.cicerone.androidx.FragmentScreen

class FilmsScreen  {

    companion object {

        fun createFragment() = FragmentScreen {
            FilmsFragment.newInstance()
        }

    }

}
