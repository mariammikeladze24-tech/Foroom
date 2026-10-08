package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class ConversationPage {
    val chatTitle = onView(withId(com.example.design_system.R.id.chatNameTextView))
    val messageInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.messageInput)))
    )
    val sendButton = onView(withId(R.id.sendMessageButton))
    val backButton = onView(withId(R.id.closeButton))

    fun messageItem(text: String) = onView(withText(text))
}