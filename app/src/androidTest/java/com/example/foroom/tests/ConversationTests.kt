package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class ConversationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()
    private val conversationSteps = ConversationSteps()

    private val userA = Constants.USER_A
    private val userB = Constants.USER_B
    private val pass = Constants.DEFAULT_PASSWORD

    private fun loginAs(username: String) {
        if (!loginSteps.isLoginScreenVisible()) {
            profileSteps.navigateToProfile()
            profileSteps.signOut()
        }
        loginSteps.authenticateUser(username, pass)
        chatSteps.navigateToChats()
    }

    @Test
    fun sendAndVerifyMessageInJohnWeek() {
        loginAs(userA)

        chatSteps.searchForChatSession(Constants.CHAT_JOHN_WEEK)
        chatSteps.openChatSession(Constants.CHAT_JOHN_WEEK)
        conversationSteps.verifyConversationIsOpen(Constants.CHAT_JOHN_WEEK)

        val uniqueSuffix = UUID.randomUUID().toString().substring(0, 5)
        val message = "${Constants.MSG_DRINK_PREFIX}$uniqueSuffix"

        conversationSteps.sendMessage(message)
        conversationSteps.verifyMessageDisplayed(message)

        conversationSteps.closeChat()
        chatSteps.searchForChatSession(Constants.CHAT_JOHN_WEEK)
        chatSteps.openChatSession(Constants.CHAT_JOHN_WEEK)
        conversationSteps.verifyMessageDisplayed(message)
    }

    @Test
    fun sendQuestionInPersonalChat() {
        loginAs(userA)

        val chatName = Constants.CHAT_PERSONAL
        chatSteps.searchForChatSession(chatName)
        chatSteps.openChatSession(chatName)
        conversationSteps.verifyConversationIsOpen(chatName)

        val uniqueSuffix = UUID.randomUUID().toString().substring(0, 5)
        val question = "${Constants.MSG_QUESTION_PREFIX}$uniqueSuffix"

        conversationSteps.sendMessage(question)
        conversationSteps.verifyMessageDisplayed(question)
    }

    @Test
    fun continueConversationUsingAnotherAccount() {
        loginAs(userA)

        chatSteps.searchForChatSession(Constants.CHAT_SOMETHING)
        chatSteps.openChatSession(Constants.CHAT_SOMETHING)

        val uniqueSuffix = UUID.randomUUID().toString().substring(0, 5)
        val greeting = "${Constants.MSG_GREETING_PREFIX}$uniqueSuffix"
        conversationSteps.sendMessage(greeting)
        conversationSteps.verifyMessageDisplayed(greeting)

        for (i in 1..25) {
            conversationSteps.sendMessage("${Constants.MSG_FILLER_PREFIX}$i")
        }
        conversationSteps.closeChat()

        loginAs(userB)
        chatSteps.searchForChatSession(Constants.CHAT_SOMETHING)
        chatSteps.openChatSession(Constants.CHAT_SOMETHING)

        conversationSteps.swipeToFindMessage(greeting)
        conversationSteps.verifyMessageWithSenderDisplayed(greeting, userA)

        val reply = "${Constants.MSG_REPLY_PREFIX}$uniqueSuffix"
        conversationSteps.sendMessage(reply)
        conversationSteps.verifyMessageDisplayed(reply)
        conversationSteps.closeChat()

        loginAs(userA)
        chatSteps.searchForChatSession(Constants.CHAT_SOMETHING)
        chatSteps.openChatSession(Constants.CHAT_SOMETHING)
        conversationSteps.verifyMessageWithSenderDisplayed(reply, userB)
    }
}