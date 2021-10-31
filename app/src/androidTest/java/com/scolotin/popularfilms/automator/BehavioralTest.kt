package com.scolotin.popularfilms.automator

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 21)
class BehavioralTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val packageName = context.packageName

    @Before
    fun setup() {
        uiDevice.pressHome()

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
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

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)

        val filmTitle = uiDevice.findObject(By.res(packageName, "filmTitle"))
        val metadata = uiDevice.findObject(By.res(packageName, "metadata"))
        val genre = uiDevice.findObject(By.res(packageName, "genre"))
        val rate = uiDevice.findObject(By.res(packageName, "rate"))
        val description = uiDevice.findObject(By.res(packageName, "description"))

        Assert.assertNotEquals(filmTitle.text, "")
        Assert.assertNotEquals(metadata.text, "")
        Assert.assertNotEquals(genre.text, "")
        Assert.assertNotEquals(rate.text, "")
        Assert.assertNotEquals(description.text, "")
    }

    @Test
    fun test_NumOfFilms() {
        val films = UiCollection(UiSelector().className("android.widget.FrameLayout"))

        val count = films.getChildCount(UiSelector().className("android.view.ViewGroup"))
        Assert.assertNotEquals(count, 0)
    }

    @Test
    fun test_SwipeFilms() {
        uiDevice.swipe(uiDevice.displayHeight / 2, uiDevice.displayWidth / 2,
                                                 0, uiDevice.displayWidth / 2, 5)

        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)

        val poster = uiDevice.findObject(By.res(packageName, "poster"))
        val filmTitle = uiDevice.findObject(By.res(packageName, "filmTitle"))
        val year = uiDevice.findObject(By.res(packageName, "year"))
        val rate = uiDevice.findObject(By.res(packageName, "rate"))

        Assert.assertNotEquals(poster.text, "")
        Assert.assertNotEquals(filmTitle.text, "")
        Assert.assertNotEquals(year, "")
        Assert.assertNotEquals(rate.text, "")
    }

    companion object {
        private const val TIMEOUT = 5000L
    }

}
