package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class LoginPage {
    val userNameInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.userNameInput)))
    )
    val userNameError = onView(
        allOf(withId(com.example.design_system.R.id.descriptionTextView), isDescendantOfA(withId(R.id.userNameInput)))
    )
    val passwordInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.passwordInput)))
    )
    val passwordError = onView(
        allOf(withId(com.example.design_system.R.id.descriptionTextView), isDescendantOfA(withId(R.id.passwordInput)))
    )
    val loginButton = onView(withId(R.id.logInButton))
    val signUpButton = onView(withId(R.id.signUpButton))
}