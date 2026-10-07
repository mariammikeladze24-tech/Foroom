package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class RegistrationPage {
    val userNameInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.userNameInput)))
    )
    val passwordInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.passwordInput)))
    )
    val repeatPasswordInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.repeatPasswordInput)))
    )
    val avatarList = onView(withId(R.id.listView))
    val signUpButton = onView(withId(R.id.signUpButton))

    val homeContainer = onView(withId(R.id.homeContainer))
    val navBar = onView(withId(R.id.navBar))
}