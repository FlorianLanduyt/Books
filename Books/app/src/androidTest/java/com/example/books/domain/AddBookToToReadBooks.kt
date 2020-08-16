package com.example.books.domain




import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4Builder
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.books.MainActivity
import com.example.books.R
import com.example.books.domain.models.Book
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.thread

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddBookToToReadBooks {
    //DONE

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun addSuperintelligenceToToReadList() {

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

        val addToToReadButton = onView(
            allOf(
                withId(R.id.toReadButton),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.booksPhotosGrid),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )

        addToToReadButton.perform(click())

        onView(
            allOf(
                withId(R.id.snackbar_text),
                withText(endsWith("wachtrij"))
            )
        ).check(
            matches(isDisplayed())
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