package com.scolotin.popularfilms

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.scolotin.popularfilms.presentation.MainActivity
import com.scolotin.popularfilms.presentation.films.FilmsViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityListTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_ScrollToPosition() {
        onView(withId(R.id.container))
            .perform(
                RecyclerViewActions.scrollToPosition<FilmsViewHolder>(3)
            )
    }

    @After
    fun close() {
        scenario.close()
    }

}
