package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class ChatsPage {
    val searchChatInput = onView(
        allOf(withId(com.example.design_system.R.id.inputEditText), isDescendantOfA(withId(R.id.searchChatInput)))
    )
    val chatsRecyclerView = onView(withId(R.id.chatsRecyclerView))
    val chatsNavigation = onView(withId(R.id.homeNavigationChats))
}