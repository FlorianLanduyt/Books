package com.example.books.domain


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.books.MainActivity
import com.example.books.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class OpenFilterMenu {


    //DONE


    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun openFilterMenu() {

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
            ViewActions.typeText("Superintelligence"),
            ViewActions.closeSoftKeyboard()
        )

        Thread.sleep(3000)



        val overflowMenu = Espresso.onView(
            allOf(
                childAtPosition(
                    withId(R.id.action_bar),
                    2
                )
            )
        )
        overflowMenu.perform(click())

        Thread.sleep(2000)


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