package com.hiteshchopra.cookpadclone.viewmodel

import app.cash.turbine.test
import com.hiteshchopra.cookpadclone.TestUtil.createRecipeItemDomain
import com.hiteshchopra.cookpadclone.TestUtil.createRecipeItemUIModel1
import com.hiteshchopra.cookpadclone.collections.CollectionRecipeFragmentVM
import com.hiteshchopra.cookpadclone.models.recipe.RecipeItemUIMapper
import com.hiteshchopra.cookpadclone.models.recipe.RecipeStepsUIMapper
import com.hiteshchopra.cookpadclone.models.recipe.RecipeUserUIMapper
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState
import com.hiteshchopra.domain.SafeResult
import com.hiteshchopra.domain.model.RecipeItemDomain
import com.hiteshchopra.domain.usecase.GetRecipeByCollectionIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit Test for [CollectionRecipeFragmentVM]
 */
@ExperimentalCoroutinesApi
class CollectionRecipeFragmentVMTest {

  private lateinit var collectionRecipeFragmentVM: CollectionRecipeFragmentVM

  /* Mappers */
  private var recipeStepsUIMapper = RecipeStepsUIMapper()
  private var recipeUserUIMapper = RecipeUserUIMapper()
  private var recipeItemUIMapper = RecipeItemUIMapper(recipeStepsUIMapper, recipeUserUIMapper)

  @Before
  fun setup() {
    Dispatchers.setMain(Dispatchers.Unconfined)
    MockKAnnotations.init(this, relaxUnitFun = true)
  }

  /**
   * Positive -: To verify that the data returned by the UseCase matches
   *  with the data which will be returned by ViewState and provided to UI.
   * In this test, the data supplied is same to both so it should match(assert)
   * to pass the test.
   */

  @Test
  fun `data from use case matches with viewState data should return true`() = runTest {
    val getRecipeByCollectionIdUseCase = mockk<GetRecipeByCollectionIdUseCase>()
    collectionRecipeFragmentVM =
      CollectionRecipeFragmentVM(getRecipeByCollectionIdUseCase, recipeItemUIMapper)

    val item = createRecipeItemDomain()
    val listItem: List<RecipeItemDomain> = arrayListOf(item)

    coEvery {
      getRecipeByCollectionIdUseCase.perform(any())
    } returns SafeResult.Success(listItem)

    collectionRecipeFragmentVM.getRecipesByCollectionID(1)
    collectionRecipeFragmentVM.viewState
      .test {
        assertEquals(
          (expectMostRecentItem() as ViewState.SuccessWithData<List<RecipeItemDomain>>).data,
          listItem
        )
        cancelAndIgnoreRemainingEvents()
      }
  }

  /**
   * Negative -: To verify that the data returned by the UseCase matches
   * with the data which will be returned by ViewState and provided to UI.
   * In this test, the data supplied is same to different so it should not
   * match(assert!=) to pass the test.
   */

  @Test
  fun `data from use case does not match with viewState data should return false`() = runBlocking {
    val getRecipeByCollectionIdUseCase = mockk<GetRecipeByCollectionIdUseCase>()
    collectionRecipeFragmentVM =
      CollectionRecipeFragmentVM(getRecipeByCollectionIdUseCase, recipeItemUIMapper)

    val item = createRecipeItemDomain()
    val listItem: List<RecipeItemDomain> = arrayListOf(item)
    /* Note-: the data does not match as 2 items are provided instead of 1 as in the above list */
    val listItem2: List<RecipeItemDomain> = arrayListOf(item, item)

    coEvery {
      getRecipeByCollectionIdUseCase.perform(any())
    } returns SafeResult.Success(listItem)

    collectionRecipeFragmentVM.getRecipesByCollectionID(1)
    collectionRecipeFragmentVM.viewState
      .test {
        assertNotEquals(
          (expectMostRecentItem() as ViewState.SuccessWithData<List<RecipeItemDomain>>).data,
          listItem2
        )
      }
  }

  /**
   * Test to verify that recipeUIMapper works as intended
   * and converts domain model to same UI model and data when data
   * is same.
   */

  @Test
  fun `verify recipeItemUIMapper converts domain model with same data to ui model with same data should return true`() {
    val domainModel = createRecipeItemDomain()

    val uiModelFromMapper = recipeItemUIMapper.mapToPresentation(domainModel)
    val expectedOutput = createRecipeItemUIModel1()
    assertEquals(uiModelFromMapper, expectedOutput)
  }

  /**
   * Verify that the default value of ViewModel ViewState is ViewState.Loading
   */

  @Test
  fun `the default value of viewModel ViewState is Loading should return true`() = runBlocking {
    val getRecipeByCollectionIdUseCase = mockk<GetRecipeByCollectionIdUseCase>()
    collectionRecipeFragmentVM =
      CollectionRecipeFragmentVM(getRecipeByCollectionIdUseCase, recipeItemUIMapper)

    collectionRecipeFragmentVM.viewState
      .test {
        assertEquals(
          expectMostRecentItem(),
          ViewState.Loading
        )
      }
  }

  /**
   * Verify that ViewState also returns Failure
   * when UseCase returns failure.
   */

  @Test
  fun `viewState returns failure when UseCase returns failure should return true`() = runBlocking {
    val getRecipeByCollectionIdUseCase = mockk<GetRecipeByCollectionIdUseCase>()
    collectionRecipeFragmentVM =
      CollectionRecipeFragmentVM(getRecipeByCollectionIdUseCase, recipeItemUIMapper)

    val customException = TestCustomException()
    coEvery {
      getRecipeByCollectionIdUseCase.perform(any())
    } returns SafeResult.Failure(customException)

    collectionRecipeFragmentVM.getRecipesByCollectionID(1)
    collectionRecipeFragmentVM.viewState
      .test {
        assertEquals(
          (expectMostRecentItem() as ViewState.Failure).exception,
          customException
        )
      }
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

}

class TestCustomException : Exception()