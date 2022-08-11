package com.atakanmadanoglu.artbookapplication.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.atakanmadanoglu.artbookapplication.R
import com.atakanmadanoglu.artbookapplication.adapter.ImageRecyclerViewAdapter
import com.atakanmadanoglu.artbookapplication.getOrAwaitValue
import com.atakanmadanoglu.artbookapplication.launchFragmentInHiltContainer
import com.atakanmadanoglu.artbookapplication.repo.FakeArtRepository
import com.atakanmadanoglu.artbookapplication.viewmodel.ArtViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImagesApiFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun selectImage() {
        val navController = Mockito.mock(NavController::class.java)
        val selectedImageUrl = "test.com"
        val testViewModel = ArtViewModel(FakeArtRepository())

        launchFragmentInHiltContainer<ImagesApiFragment>(
            factory = fragmentFactory
        ) {
            viewModel = testViewModel
            Navigation.setViewNavController(requireView(), navController)
            imageRecyclerViewAdapter.submitList(listOf(selectedImageUrl))
        }

        Espresso.onView(ViewMatchers.withId(R.id.apiImagesRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ImageRecyclerViewAdapter.ImageViewHolder>(
                0, ViewActions.click()
            ))

        Mockito.verify(navController).popBackStack()

        assertThat(testViewModel.selectedImage.getOrAwaitValue())
            .isEqualTo(selectedImageUrl)
    }
}