package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import com.example.foroom.constants.Constants
import com.example.foroom.Helper.waitForViewVisible
import com.example.foroom.Helper.waitUntilVisible
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class ChatSteps {
    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()

    fun navigateToChats() {
        chatsPage.chatsNavigation.perform(click())
    }

    fun initiateNewChatCreation() {
        createChatPage.createChatNavigation.perform(click())
    }

    fun createChatSession(chatName: String) {
        createChatPage.chatNameInput.perform(replaceText(chatName), closeSoftKeyboard())
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
        chatsPage.searchChatInput.waitUntilVisible(Constants.WAIT_TIMEOUT_SEC.toLong())
        chatsPage.searchChatInput.perform(replaceText(chatName), closeSoftKeyboard())
        Thread.sleep(1000)
    }

    fun openChatSession(chatName: String) {
        val chatButtonMatcher = allOf(
            withId(R.id.sendMessageButton),
            isDescendantOfA(allOf(
                hasDescendant(withText(chatName)),
                isDisplayed()
            ))
        )

        chatButtonMatcher.waitForViewVisible(Constants.WAIT_TIMEOUT_SEC)
        onView(chatButtonMatcher).perform(click())
    }

    fun isChatInList(chatName: String): Boolean {
        return try {
            onView(allOf(withId(com.example.design_system.R.id.chatTitleTextView), withText(chatName))).check(matches(isDisplayed()))
            true
        } catch (_: Throwable) {
            false
        }
    }

    fun verifyChatAppearsInList(chatName: String) {
        chatsPage.chatsRecyclerView.check(matches(hasDescendant(withText(chatName))))
    }
}