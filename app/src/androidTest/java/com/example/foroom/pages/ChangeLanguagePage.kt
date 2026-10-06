package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class ChangeLanguagePage {
    val languageButtonGeo = onView(withId(R.id.languageButtonGeo))
    val languageButtonEng = onView(withId(R.id.languageButtonEng))
}