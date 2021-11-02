package com.scolotin.popularfilms

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

internal val context = ApplicationProvider.getApplicationContext<Context>()

internal val packageName = context.packageName

internal val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

internal const val TIMEOUT = 5000L

internal const val EMPTY_STRING = ""

internal fun waitApp() {
    uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
}

internal fun swipeLeft() {
    uiDevice.swipe (uiDevice.displayHeight / 2, uiDevice.displayWidth / 2,
        0, uiDevice.displayWidth / 2, 5)
}
