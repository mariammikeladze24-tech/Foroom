package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.constants.Constants
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Before
    fun setUp() {
        try {
            loginSteps.verifyScreenIsVisible()
            loginSteps.authenticateUser(Constants.TEST_USERNAME, Constants.CURRENT_PASSWORD)
        } catch (e: Exception) {
            
        }
    }

    @After
    fun tearDown() {
        
    }

    @Test
    fun changePasswordAndVerify() {
        profileSteps.navigateToProfile()
        profileSteps.updatePassword(Constants.NEW_PASSWORD)
        profileSteps.verifyReturnToLoginScreen()

        loginSteps.authenticateUser(Constants.TEST_USERNAME, Constants.NEW_PASSWORD)

        profileSteps.navigateToProfile()
        profileSteps.updatePassword(Constants.CURRENT_PASSWORD)
        profileSteps.verifyReturnToLoginScreen()
    }

    @Test
    fun changeLanguageGeorgianAndEnglish() {
        profileSteps.navigateToProfile()

        profileSteps.switchLanguageToGeorgian()
        profileSteps.verifyGeorgianProfileLabels()

        profileSteps.switchLanguageToEnglish()
        profileSteps.verifyEnglishProfileLabels()

        profileSteps.switchLanguageToGeorgian()
        profileSteps.verifyGeorgianProfileLabels()

        profileSteps.switchLanguageToEnglish()
    }

    @Test
    fun createChatAndFindInList() {
        val uniqueChatName = "${Constants.TEST_CHAT_PREFIX}${UUID.randomUUID().toString().substring(0, 5)}"

        chatSteps.initiateNewChatCreation()
        chatSteps.createChatSession(uniqueChatName)

        chatSteps.verifyChatScreenOpens(uniqueChatName)
        chatSteps.leaveChatScreen()

        chatSteps.searchForChatSession(uniqueChatName)
        chatSteps.verifyChatAppearsInList(uniqueChatName)
    }
}