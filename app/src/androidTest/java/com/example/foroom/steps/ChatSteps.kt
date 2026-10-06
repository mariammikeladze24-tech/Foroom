package com.example.foroom.steps

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {
    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()

    fun initiateNewChatCreation() {
        createChatPage.createChatNavigation.perform(click())
    }

    fun createChatSession(chatName: String) {
        createChatPage.chatNameInput.perform(typeText(chatName), closeSoftKeyboard())
        createChatPage.chatImageChooser.perform(click())
        createChatPage.createChatButton.perform(click())
    }

    fun verifyChatScreenOpens(expectedName: String) {
        createChatPage.getChatTitleTextView(expectedName).check(matches(isDisplayed()))
    }

    fun leaveChatScreen() {
        createChatPage.closeButton.perform(click())
    }

    fun searchForChatSession(chatName: String) {
        chatsPage.searchChatInput.perform(typeText(chatName), closeSoftKeyboard())
    }

    fun verifyChatAppearsInList(chatName: String) {
        chatsPage.chatsRecyclerView.check(matches(hasDescendant(withText(chatName))))
    }
}