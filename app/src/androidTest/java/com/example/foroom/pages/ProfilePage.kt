package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class ProfilePage {
    val changePasswordItem = onView(withId(R.id.changePasswordItem))
    val changeLanguageItem = onView(withId(R.id.changeLanguageItem))
    val signOutItem = onView(withId(R.id.signOutItem))
    val profileNavigation = onView(withId(R.id.homeNavigationProfile))
}