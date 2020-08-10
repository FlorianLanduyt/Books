package com.example.books.domain

import android.view.View
import android.view.ViewGroup
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import androidx.test.runner.AndroidJUnit4
import com.example.books.MainActivity
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddBookFavorite {

    @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun addBookFavorite() {
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