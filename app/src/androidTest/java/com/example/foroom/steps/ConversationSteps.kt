package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.pages.ConversationPage
import com.example.foroom.constants.Constants
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.Helper.waitForViewVisible
import com.example.foroom.Helper.scrollSlowlyUp
import com.example.foroom.Helper.scrollSlowlyDown
import org.hamcrest.CoreMatchers.allOf

class ConversationSteps {
    private val page = ConversationPage()

    fun verifyConversationIsOpen(expectedTitle: String) {
        page.chatTitle.waitUntilVisible(Constants.WAIT_TIMEOUT_SEC.toLong())
        page.chatTitle.check(matches(withText(expectedTitle)))
    }

    fun sendMessage(text: String) {
        page.messageInput.waitUntilVisible(Constants.WAIT_TIMEOUT_SEC.toLong())
        page.messageInput.perform(replaceText(text), closeSoftKeyboard())

        var attempts = 0
        while (attempts < 10) {
            try {
                page.sendButton.check(matches(isEnabled()))
                break
            } catch (e: Throwable) {
                Thread.sleep(200)
                attempts++
            }
        }

        page.sendButton.perform(click())
    }

    fun verifyMessageDisplayed(text: String) {
        var attempts = 0
        while (attempts < 50) {
            try {
                page.messageItem(text).check(matches(isDisplayed()))
                break
            } catch (e: Throwable) {
                scrollSlowlyDown()
                attempts++
            }
        }
        page.messageItem(text).check(matches(isDisplayed()))
    }

    fun verifyMessageWithSenderDisplayed(text: String, senderName: String) {
        val messageWithSenderMatcher = allOf(
            withText(text),
            hasSibling(hasDescendant(withText(senderName)))
        )

        try {
            onView(messageWithSenderMatcher).check(matches(isDisplayed()))
            return
        } catch (e: Throwable) {
            // If it's a newly sent message hidden by the keyboard, scroll DOWN to find it.
            var attempts = 0
            while (attempts < 10) {
                try {
                    onView(messageWithSenderMatcher).check(matches(isDisplayed()))
                    return
                } catch (err: Throwable) {
                    scrollSlowlyDown()
                    attempts++
                }
            }
        }
        onView(messageWithSenderMatcher).check(matches(isDisplayed()))
    }

    fun closeChat() {
        page.backButton.waitUntilVisible(Constants.WAIT_TIMEOUT_SEC.toLong())
        page.backButton.perform(click())
    }

    fun swipeToFindMessage(text: String) {
        var attempts = 0
        while (attempts < 50) {
            try {
                onView(withText(text)).check(matches(isDisplayed()))
                return
            } catch (e: Throwable) {
                scrollSlowlyUp()
                attempts++
            }
        }
    }
}