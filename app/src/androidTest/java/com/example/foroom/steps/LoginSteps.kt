package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.pages.LoginPage

class LoginSteps {
    private val page = LoginPage()

    fun verifyScreenIsVisible() {
        page.loginButton.check(matches(isDisplayed()))
    }

    fun isLoginScreenVisible(): Boolean {
        return try {
            page.loginButton.check(matches(isDisplayed()))
            true
        } catch (_: Throwable) {
            false
        }
    }

    fun authenticateUser(username: String, pass: String) {
        page.userNameInput.perform(replaceText(username), closeSoftKeyboard())
        page.passwordInput.perform(replaceText(pass), closeSoftKeyboard())
        page.loginButton.perform(click())
    }

    fun navigateToRegistration() {
        page.signUpButton.perform(click())
    }

    fun verifyInvalidPasswordRejection() {
        page.passwordError.check(matches(isDisplayed()))
    }

    fun verifyInvalidAccountRejection() {
        page.userNameError.check(matches(isDisplayed()))
        page.passwordError.check(matches(isDisplayed()))
    }
}