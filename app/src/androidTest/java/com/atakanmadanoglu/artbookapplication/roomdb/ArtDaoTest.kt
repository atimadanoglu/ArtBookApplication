package com.atakanmadanoglu.artbookapplication.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.atakanmadanoglu.artbookapplication.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var dao: ArtDao

    @Inject
    @Named("testDatabase")
    lateinit var database: ArtDB

    @Before
    fun setup() {
        /*database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArtDB::class.java
        ).allowMainThreadQueries().build()*/

        hiltRule.inject()

        dao = database.artDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertArt() = runTest {
        val art = Art("Mona Lisa", "Da Vinci", 1500, "test.com", 1)
        dao.insertArt(art)
        val artList = dao.observeArts().getOrAwaitValue()
        assertThat(artList).contains(art)
    }

    @Test
    fun deleteArt() = runTest {
        val art = Art("Mona Lisa", "Da Vinci", 1500, "test.com", 1)
        dao.insertArt(art)
        dao.deleteArt(art)
        val artList = dao.observeArts().getOrAwaitValue()
        assertThat(artList).doesNotContain(art)
    }










}