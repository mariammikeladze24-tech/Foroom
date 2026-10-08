package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class CreateChatPage {
    val createChatNavigation = onView(withId(R.id.homeNavigationCreateChat))
    val chatNameInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.chatNameInput)))
    )
    val chatImageChooser = onView(withId(R.id.chatImageChooser))
    val createChatButton = onView(withId(R.id.createChatButton))
    val closeButton = onView(withId(R.id.closeButton))
    fun getChatTitleTextView(expectedName: String) = onView(
        allOf(
            withId(com.example.design_system.R.id.chatNameTextView),
            withText(expectedName)
        )
    )
}