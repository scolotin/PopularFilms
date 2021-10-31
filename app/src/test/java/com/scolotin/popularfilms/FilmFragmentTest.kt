package com.scolotin.popularfilms

import android.os.Build
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textview.MaterialTextView
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.presentation.film.FilmFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.LOLLIPOP])
class FilmFragmentTest {

    private lateinit var scenario: FragmentScenario<FilmFragment>

    @Before
    fun setup() {
        scenario = FragmentScenario.launch(FilmFragment::class.java)
    }

    @Test
    fun fragment_AssertNotNull() {
        scenario.onFragment {
            assertNotNull(it)
        }
    }

    @Test
    fun fragment_IsResumed() {
        scenario.onFragment {
            assertEquals(Lifecycle.State.RESUMED, it.lifecycle.currentState)
        }
    }

    @Test
    fun fragmentFilmTitle_NotNull() {
        scenario.onFragment {
            val filmTitle = it.view?.findViewById<MaterialTextView>(R.id.filmTitle)
            assertNotNull(filmTitle)
        }
    }

    @Test
    fun fragmentFilmTitle_IsVisible() {
        scenario.onFragment {
            val filmTitle = it.view?.findViewById<MaterialTextView>(R.id.filmTitle)
            assertEquals(View.VISIBLE, filmTitle?.visibility)
        }
    }

    @Test
    fun fragmentMetaData_NotNull() {
        scenario.onFragment {
            val metaData = it.view?.findViewById<MaterialTextView>(R.id.metadata)
            assertNotNull(metaData)
        }
    }

    @Test
    fun fragmentMetaData_IsVisible() {
        scenario.onFragment {
            val metaData = it.view?.findViewById<MaterialTextView>(R.id.metadata)
            assertEquals(View.VISIBLE, metaData?.visibility)
        }
    }

    @Test
    fun fragmentGenre_NotNull() {
        scenario.onFragment {
            val genre = it.view?.findViewById<MaterialTextView>(R.id.genre)
            assertNotNull(genre)
        }
    }

    @Test
    fun fragmentGenre_IsVisible() {
        scenario.onFragment {
            val genre = it.view?.findViewById<MaterialTextView>(R.id.genre)
            assertEquals(View.VISIBLE, genre?.visibility)
        }
    }

    @Test
    fun fragmentRate_NotNull() {
        scenario.onFragment {
            val rate = it.view?.findViewById<MaterialTextView>(R.id.rate)
            assertNotNull(rate)
        }
    }

    @Test
    fun fragmentRate_IsVisible() {
        scenario.onFragment {
            val rate = it.view?.findViewById<MaterialTextView>(R.id.rate)
            assertEquals(View.VISIBLE, rate?.visibility)
        }
    }

    @Test
    fun fragmentDescription_NotNull() {
        scenario.onFragment {
            val description = it.view?.findViewById<MaterialTextView>(R.id.description)
            assertNotNull(description)
        }
    }

    @Test
    fun fragmentDescription_IsVisible() {
        scenario.onFragment {
            val description = it.view?.findViewById<MaterialTextView>(R.id.description)
            assertEquals(View.VISIBLE, description?.visibility)
        }
    }

    @Test
    fun fragmentShowFilm_IsWorking() {
        scenario.onFragment {
            val film = Film(title = "A", releaseDate = "B", genre = "C", voteAverage = 0.1F, overview = "D")
            it.showFilm(film)
            val filmTitle = it.view?.findViewById<MaterialTextView>(R.id.filmTitle)
            assertEquals("A", filmTitle?.text)
            val metaData = it.view?.findViewById<MaterialTextView>(R.id.metadata)
            assertEquals("B", metaData?.text)
            val genre = it.view?.findViewById<MaterialTextView>(R.id.genre)
            assertEquals("C", genre?.text)
            val rate = it.view?.findViewById<MaterialTextView>(R.id.rate)
            assertEquals("0.1", rate?.text)
            val description = it.view?.findViewById<MaterialTextView>(R.id.description)
            assertEquals("D", description?.text)
        }
    }

    @Test
    fun fragmentNewInstance_NotNull() {
        val film = Film(title = "A", releaseDate = "B", genre = "C", voteAverage = 0.1F, overview = "D")
        val inst = FilmFragment.newInstance(film)
        assertNotNull(inst)
    }

    @Test
    fun fragmentNewInstance_HasExtras() {
        val film = Film(title = "A", releaseDate = "B", genre = "C", voteAverage = 0.1F, overview = "D")
        val inst = FilmFragment.newInstance(film)
        val arguments = inst.arguments
        assertNotNull(arguments)
    }

    @Test
    fun fragmentNewInstance_HasValue() {
        val film = Film(title = "A", releaseDate = "B", genre = "C", voteAverage = 0.1F, overview = "D")
        val inst = FilmFragment.newInstance(film)
        val arguments = inst.arguments
        assertEquals(film, arguments?.getParcelable(FilmFragment.ARG_FILM))
    }

}
