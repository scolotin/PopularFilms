package com.scolotin.popularfilms

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.viewpager2.widget.ViewPager2
import com.scolotin.popularfilms.presentation.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_AssertNotNull() {
        activityScenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, activityScenario.state)
    }

    @Test
    fun activityViewPager_NotNull() {
        activityScenario.onActivity {
            val viewPager = it.findViewById<ViewPager2>(R.id.preview)
            TestCase.assertNotNull(viewPager)
        }
    }

    @Test
    fun activityViewPagerItem_IsCompletelyDisplayed() {
        onView(withId(R.id.preview)).check(matches(isCompletelyDisplayed()))
    }

    @After
    fun close() {
        activityScenario.close()
    }
}
