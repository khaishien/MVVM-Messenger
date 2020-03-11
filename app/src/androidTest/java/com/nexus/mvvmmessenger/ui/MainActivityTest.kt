package com.nexus.mvvmmessenger.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.nexus.mvvmmessenger.R
import com.nexus.mvvmmessenger.ui.main.MainActivity
import org.junit.Test
import org.junit.Rule

class MainActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testEndOfMinDialog() {
        onView(withText(R.string.timer_desc)).inRoot(isDialog())
            .check(matches(isDisplayed()))
        onView(withText(R.string.title_ok)).perform(click())
    }

    @Test
    fun testMessageListExist() {
        var oriCount = 7
        onView(withId(R.id.message_list)).check(RecyclerViewAssertions.hasItemsCount(oriCount))
        onView(withId(R.id.message_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                longClick()
            )
        ) //long click on first item

        onView(withText(R.string.desc_dialog_delete)).inRoot(isDialog())
            .check(matches(isDisplayed()))

        onView(withText(R.string.title_delete)).perform(click())

        onView(withText(R.string.desc_dialog_delete)).inRoot(isDialog()).check(doesNotExist())
        onView(withId(R.id.message_list)).check(RecyclerViewAssertions.hasItemsCount(oriCount - 2)) // 1 is section , 1 is item
    }


    @Test
    fun testEditTextAndSubmit() {

        var oriCount = 10
        onView(withId(R.id.message_list)).check(RecyclerViewAssertions.hasItemsCount(oriCount))
        onView(withId(R.id.et_new_message)).perform(clearText(), typeText("ABC"))
        onView(withId(R.id.fab_sent)).perform(click())

        onView(withId(R.id.message_list)).check(RecyclerViewAssertions.hasItemsCount(oriCount + 1))
    }
}