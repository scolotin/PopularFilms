package com.scolotin.popularfilms.presentation.film

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.scolotin.popularfilms.model.Film

class FilmScreen {

    companion object {

        fun createFragment(film: Film) = FragmentScreen {
            FilmFragment.newInstance(film)
        }

    }

}
