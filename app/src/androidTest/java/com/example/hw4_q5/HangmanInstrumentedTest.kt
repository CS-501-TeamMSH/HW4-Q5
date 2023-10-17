package com.example.hw4_q5
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
//import java.util.EnumSet.allOf

@RunWith(AndroidJUnit4::class)
class HangmanInstrumentedTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var hangmanGame: HangmanGame
    private lateinit var word: String
    private lateinit var keyboardFragment: KeyboardFragment
    private lateinit var hangmanFragment: Hangman
    private var gameEnded = false

//    @Before
//    fun setup() {
//        scenario = ActivityScenario.launch(MainActivity::class.java)
//        hangmanGame = HangmanGame()
//    }
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.hw4_q5", appContext.packageName)
    }

    @Test
    fun testClickOnHintButton() {
        onView(allOf(withId(R.id.hintbutton), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click())
        onView(allOf(withId(R.id.hinttext), withText("It's a fruit"))).perform(click())
//        onView(withId(R.id.hintbutton)).perform(click())
//        onView(withId(R.id.hinttext)).check(matches(withText(R.string.hinttext)))
    }

    @Test
    fun testPlayAgainButton() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(allOf(withId(R.id.playAgainButton), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click())
        onView(
            Matchers.allOf(
                withId(R.id.playAgainButton),
                withEffectiveVisibility(ViewMatchers.Visibility.GONE)
            )
        ).check(matches(ViewMatchers.isDisplayed()))

    }

}



