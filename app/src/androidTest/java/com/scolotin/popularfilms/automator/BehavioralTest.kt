package com.scolotin.popularfilms.automator

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.uiautomator.*
import com.scolotin.popularfilms.*
import com.scolotin.popularfilms.context
import com.scolotin.popularfilms.packageName
import com.scolotin.popularfilms.uiDevice
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 21)
class BehavioralTest {

    @Before
    fun setup() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        waitApp()
    }

    @Test
    fun test_MainActivityIsStarted() {
        val container = uiDevice.findObject(By.res(packageName, "container"))

        Assert.assertNotNull(container)
    }

    @Test
    fun test_FilmScreen() {
        val toFilm = uiDevice.findObject(By.res(packageName, "container"))
        toFilm.click()

        waitApp()

        val filmTitle = uiDevice.findObject(By.res(packageName, "filmTitle"))
        val metadata = uiDevice.findObject(By.res(packageName, "metadata"))
        val genre = uiDevice.findObject(By.res(packageName, "genre"))
        val rate = uiDevice.findObject(By.res(packageName, "rate"))
        val description = uiDevice.findObject(By.res(packageName, "description"))

        Assert.assertNotEquals(filmTitle.text, EMPTY_STRING)
        Assert.assertNotEquals(metadata.text, EMPTY_STRING)
        Assert.assertNotEquals(genre.text, EMPTY_STRING)
        Assert.assertNotEquals(rate.text, EMPTY_STRING)
        Assert.assertNotEquals(description.text, EMPTY_STRING)
    }

    @Test
    fun test_NumOfFilms() {
        val films = UiCollection(UiSelector().className("android.widget.FrameLayout"))

        val count = films.getChildCount(UiSelector().className("android.view.ViewGroup"))
        Assert.assertNotEquals(count, 0)
    }

    @Test
    fun test_SwipeFilms() {
        swipeLeft()
        waitApp()

        val poster = uiDevice.findObject(By.res(packageName, "poster"))
        val filmTitle = uiDevice.findObject(By.res(packageName, "filmTitle"))
        val year = uiDevice.findObject(By.res(packageName, "year"))
        val rate = uiDevice.findObject(By.res(packageName, "rate"))

        Assert.assertNotEquals(poster.text, EMPTY_STRING)
        Assert.assertNotEquals(filmTitle.text, EMPTY_STRING)
        Assert.assertNotEquals(year, EMPTY_STRING)
        Assert.assertNotEquals(rate.text, EMPTY_STRING)
    }

}
