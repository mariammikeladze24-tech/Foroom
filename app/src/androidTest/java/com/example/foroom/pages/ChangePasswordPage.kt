package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class ChangePasswordPage {
    val passwordInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.passwordInput)))
    )
    val repeatPasswordInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.repeatPasswordInput)))
    )
    val actionButton = onView(withId(com.example.design_system.R.id.actionButton))
}