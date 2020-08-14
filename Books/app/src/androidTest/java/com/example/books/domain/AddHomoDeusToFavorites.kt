package com.example.books.domain

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
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
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddHomoDeusToFavorites {

   @Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


//    @Test
//    fun addBookFavorite() {
//        Thread.sleep(3000)
//
//        val materialCardView = Espresso.onView(
//            Matchers.allOf(
//                withId(R.id.),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.myNavHostFragment),
//                        0
//                    ),
//                    0
//                ),
//                ViewMatchers.isDisplayed()
//            )
//        )
//        materialCardView.perform(ViewActions.click())
//    }



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