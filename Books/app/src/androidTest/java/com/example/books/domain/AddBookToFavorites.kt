package com.example.books.domain

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
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
class AddBookToFavorites {


    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun addHomoDeusToFavorite() {

        val searchIcon = Espresso.onView(
            allOf(
                withId(R.id.searchButton),
                isDisplayed()
            )

        )
        searchIcon.perform(click())

        val searchField = Espresso.onView(
            allOf(
                withId(R.id.searchText),
                isDisplayed()
            )

        )
        searchField.perform(click())

        searchField.perform(
            ViewActions.typeText("Homo deus"),
            ViewActions.closeSoftKeyboard()
        )

        Thread.sleep(3000)

        val constraintLayout = Espresso.onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.booksPhotosGrid)
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        constraintLayout.perform(click())

        Thread.sleep(3000)

        val floatingActionButton = Espresso.onView(
            allOf(
                withId(R.id.button_add_to_favorites),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())
        Thread.sleep(3000)
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