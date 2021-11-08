package com.scolotin.popularfilms

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.scolotin.popularfilms.presentation.films.FilmsFragment
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<FilmsFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_AssertNotNull() {
        scenario.onFragment {
            Assert.assertNotNull(it)
        }
    }

    @Test
    fun fragment_IsResumed() {
        scenario.onFragment {
            Assert.assertEquals(Lifecycle.State.RESUMED, it.lifecycle.currentState)
        }
    }

    @Test
    fun fragment_PosterIsVisible() {
        val assertion = matches(withId(R.id.poster))
        onView(withId(R.id.preview)).check(assertion)
    }

    @Test
    fun fragment_PosterClick() {
        onView(withId(R.id.container)).perform(click())

        val assertion = matches(withId(R.id.description))
        onView(withId(R.id.container)).check(assertion)
    }

}
