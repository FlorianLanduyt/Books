package com.example.books.domain

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.books.MainActivity
import com.example.books.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class FinishedList{
    //DONE

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun finishedListViaButtonFromHomeScreen() {
        val finishedBooksButton = Espresso.onView(
            allOf(
                withId(R.id.toFinishedBooks),
                isDisplayed()
            )
        )

        finishedBooksButton.perform(click())
        Thread.sleep(1000)

        Espresso.onView(
            withId(R.id.action_bar)
        ).check(
            matches(
                hasDescendant(
                    withText("Gelezen")
                )
            )
        )

        Thread.sleep(1000)

    }


    @Test
    fun finishedListViaDrawer(){
        Espresso.onView(
            withContentDescription(R.string.nav_app_bar_open_drawer_description)
        ).perform(click())

        val item = Espresso.onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.navView),
                            0
                        )
                    ),
                    6
                ),
                isDisplayed()
            )
        )

        item.perform(click())

        Thread.sleep(1000)

        Espresso.onView(
            withId(R.id.action_bar)
        ).check(
            matches(
                hasDescendant(
                    withText("Gelezen")
                )
            )
        )

        Thread.sleep(1000)

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                        view == parent.getChildAt(position)
            }
        }
    }



}