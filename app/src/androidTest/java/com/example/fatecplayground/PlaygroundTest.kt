package com.example.fatecplayground

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaygroundTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun myTest() {
        rule.setContent { MainScreen() }
        // Do something
        //rule.onNodeWithText("Home").performClick()

        // Check something
        Thread.sleep(5000)
    }
}