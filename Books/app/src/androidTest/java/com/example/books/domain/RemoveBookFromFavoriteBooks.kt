package com.example.books.domain

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.books.MainActivity
import com.example.books.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test

class RemoveBookFromFavoriteBooks {


    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun addTheBookToFavorites() {
        val searchIcon = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.searchButton),
                ViewMatchers.isDisplayed()
            )

        )
        searchIcon.perform(ViewActions.click())

        val searchField = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.searchText),
                ViewMatchers.isDisplayed()
            )

        )
        searchField.perform(ViewActions.click())

        searchField.perform(
            ViewActions.typeText("Homo sapien"),
            ViewActions.closeSoftKeyboard()
        )

        Thread.sleep(3000)

        val constraintLayout = Espresso.onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.booksPhotosGrid)
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        constraintLayout.perform(ViewActions.click())

        Thread.sleep(1000)

        val floatingActionButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.button_add_to_favorites),
                ViewMatchers.isDisplayed()
            )
        )
        floatingActionButton.perform(ViewActions.click())
        Thread.sleep(1000)

        val favoriteButton = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.toFavoritesButton),
                childAtPosition(
                    withId(R.id.favoriteBooksContainer),
                    2
                )
            )
        )

        favoriteButton.perform(click())

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