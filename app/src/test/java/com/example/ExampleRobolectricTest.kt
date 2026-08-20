package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.InitialCatalogData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Mysore Cauvery", appName)
  }

  @Test
  fun `verify initial catalog contains all 4 categories`() {
    val items = InitialCatalogData.items
    assertTrue(items.any { it.category == "Saree" })
    assertTrue(items.any { it.category == "Handicraft" })
    assertTrue(items.any { it.category == "Sandal Products" })
    assertTrue(items.any { it.category == "Wood Articles" })
  }
}

