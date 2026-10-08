package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.constants.Constants
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.ProfilePage
import org.hamcrest.Matchers.allOf

class ProfileSteps {
    private val profilePage = ProfilePage()
    private val passwordPage = ChangePasswordPage()
    private val languagePage = ChangeLanguagePage()
    private val loginPage = LoginPage()

    fun navigateToProfile() {
        profilePage.profileNavigation.perform(click())
    }

    fun isProfileVisibleWithUser(username: String): Boolean {
        return try {
            onView(allOf(withId(com.alternator.foroom.R.id.userNameTextView), withText(username))).check(matches(isDisplayed()))
            true
        } catch (_: Throwable) {
            false
        }
    }

    fun updatePassword(newPassword: String) {
        profilePage.changePasswordItem.perform(click())
        passwordPage.passwordInput.perform(typeText(newPassword), closeSoftKeyboard())
        passwordPage.repeatPasswordInput.perform(typeText(newPassword), closeSoftKeyboard())
        passwordPage.actionButton.perform(click())
    }

    fun verifyReturnToLoginScreen() {
        loginPage.loginButton.check(matches(isDisplayed()))
    }

    fun switchLanguageToGeorgian() {
        profilePage.changeLanguageItem.perform(click())
        languagePage.languageButtonGeo.perform(click())
    }

    fun switchLanguageToEnglish() {
        profilePage.changeLanguageItem.perform(click())
        languagePage.languageButtonEng.perform(click())
    }

    fun verifyGeorgianProfileLabels() {
        profilePage.changeLanguageItem.check(matches(ViewMatchers.hasDescendant(withText(Constants.LABEL_CHANGE_LANGUAGE_GEO))))
    }

    fun verifyEnglishProfileLabels() {
        profilePage.changeLanguageItem.check(matches(ViewMatchers.hasDescendant(withText(Constants.LABEL_CHANGE_LANGUAGE_ENG))))
    }

    fun logOut() {
        profilePage.signOutItem.perform(click())
    }

    fun signOut() {
        logOut()
    }
}