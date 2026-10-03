package com.example.foroom.steps

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.pages.RegistrationPage
import org.hamcrest.Matcher

class RegistrationSteps {
    private val page = RegistrationPage()

    fun verifyScreenIsVisible() {
        page.signUpButton.check(matches(isDisplayed()))
    }

    fun registerAccount(username: String, pass: String) {
        page.userNameInput.perform(typeText(username), closeSoftKeyboard())
        page.passwordInput.perform(typeText(pass), closeSoftKeyboard())
        page.repeatPasswordInput.perform(typeText(pass), closeSoftKeyboard())

        onView(withId(R.id.listView)).perform(clickChildViewAt(0))

        page.signUpButton.perform(click())
    }

    fun verifyRegistrationSuccess() {
        page.homeContainer.check(matches(isDisplayed()))
        page.navBar.check(matches(isDisplayed()))
    }

    private fun clickChildViewAt(childIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isDisplayed()

            override fun getDescription(): String = "Click child view at index $childIndex"

            override fun perform(uiController: UiController, view: View) {
                val parent = view as ViewGroup
                if (childIndex < parent.childCount) {
                    parent.getChildAt(childIndex).performClick()
                } else {
                    throw Exception("Child at index $childIndex does not exist")
                }
            }
        }
    }
}