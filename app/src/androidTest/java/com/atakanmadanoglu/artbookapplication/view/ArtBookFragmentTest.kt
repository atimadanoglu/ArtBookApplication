package com.atakanmadanoglu.artbookapplication.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.atakanmadanoglu.artbookapplication.R
import com.atakanmadanoglu.artbookapplication.launchFragmentInHiltContainer
import com.atakanmadanoglu.artbookapplication.views.ArtBookFragment
import com.atakanmadanoglu.artbookapplication.views.ArtBookFragmentDirections
import com.atakanmadanoglu.artbookapplication.views.ArtFragmentFactory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class ArtBookFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtBookFragmentToArtDetailsFragment() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<ArtBookFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.floatingActionButton))
            .perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            ArtBookFragmentDirections.actionArtBookFragmentToArtDetailsFragment()
        )
    }
}