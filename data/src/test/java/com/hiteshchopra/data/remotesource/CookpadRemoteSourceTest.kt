package com.hiteshchopra.data.remotesource

import com.hiteshchopra.data.MainCoroutineRule
import com.hiteshchopra.data.TestUtil
import com.hiteshchopra.data.remote.CookpadApiService
import com.hiteshchopra.data.remote.source.CookpadRemoteSource
import com.hiteshchopra.domain.SafeResult.Success
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CookpadRemoteSourceTest {

  private val testDispatcher: TestDispatcher = StandardTestDispatcher()

  @ExperimentalCoroutinesApi
  @Before
  fun setupDispatcher() {
    Dispatchers.setMain(testDispatcher)
  }

  @ExperimentalCoroutinesApi
  @After
  fun tearDownDispatcher() {
    Dispatchers.resetMain()
  }

  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()

  @Test
  fun `when api returns success, remote source should also return success with correct data mapping`() =
    runTest {
      val mockApiService = mockk<CookpadApiService>(relaxed = true)
      val inputData = TestUtil.createRecipeItemData()
      coEvery {
        mockApiService.getRecipeById(any())
      } returns inputData

      val cookpadRemoteSource = CookpadRemoteSource(
        mockApiService,
        Dispatchers.Main
      )
      val result = cookpadRemoteSource.getRecipeById(1)

      assert((result as Success<*>).data == inputData)
    }
}