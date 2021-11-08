package com.scolotin.popularfilms.automator

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import com.scolotin.popularfilms.context
import com.scolotin.popularfilms.packageName
import com.scolotin.popularfilms.uiDevice
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 21)
class InitialTest {

    @Test
    fun test_DeviceNotNull() {
        Assert.assertNotNull(uiDevice)
    }

    @Test
    fun test_AppPackageNotNull() {
        Assert.assertNotNull(packageName)
    }

    @Test
    fun test_MainActivityIntentNotNull() {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        Assert.assertNotNull(intent)
    }

}
