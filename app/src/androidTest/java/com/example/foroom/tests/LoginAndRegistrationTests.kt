package com.example.foroom.tests

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @After
    fun tearDown() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val sharedPrefsDir = File(context.applicationInfo.dataDir, "shared_prefs")

        if (sharedPrefsDir.exists() && sharedPrefsDir.isDirectory) {
            sharedPrefsDir.listFiles()?.forEach { it.delete() }
        }
    }

    @Test
    fun validUsernameAndInvalidPassword() {
        loginSteps.verifyScreenIsVisible()
        loginSteps.authenticateUser(Constants.EXISTING_USERNAME, Constants.INVALID_PASSWORD)

        loginSteps.verifyInvalidPasswordRejection()
    }

    @Test
    fun invalidUsernameAndInvalidPassword() {
        loginSteps.verifyScreenIsVisible()
        loginSteps.authenticateUser(Constants.NON_EXISTENT_USERNAME, Constants.INVALID_PASSWORD)

        loginSteps.verifyInvalidAccountRejection()
    }

    @Test
    fun successfulRegistration() {
        val uniqueUsername = "${Constants.USERNAME_PREFIX}${UUID.randomUUID().toString().substring(0, 8)}"

        loginSteps.verifyScreenIsVisible()
        loginSteps.navigateToRegistration()

        registrationSteps.verifyScreenIsVisible()
        registrationSteps.registerAccount(uniqueUsername, Constants.VALID_PASSWORD)

        registrationSteps.verifyRegistrationSuccess()
    }
}