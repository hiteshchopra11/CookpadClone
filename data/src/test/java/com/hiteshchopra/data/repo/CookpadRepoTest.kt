package com.hiteshchopra.data.repo

import com.hiteshchopra.data.TestUtil.createRecipeItemData
import com.hiteshchopra.data.TestUtil.createRecipeItemDomain
import com.hiteshchopra.data.remote.model.collection.CollectionsItemDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeItemDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeStepsDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeUserDataMapper
import com.hiteshchopra.data.remote.repo.CookpadRepo
import com.hiteshchopra.data.remote.source.ICookpadRemoteSource
import com.hiteshchopra.domain.SafeResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any

@ExperimentalCoroutinesApi
class CookpadRepoTest {

  /* Mappers */
  lateinit var collectionsItemDataMapper: CollectionsItemDataMapper
  lateinit var recipeItemDataMapper: RecipeItemDataMapper

  @Before
  fun setup() {
    collectionsItemDataMapper = CollectionsItemDataMapper()
    recipeItemDataMapper = RecipeItemDataMapper(RecipeStepsDataMapper(), RecipeUserDataMapper())
  }

  @Test
  fun `when remote source returns success, repo should also return success with correct data mapping`() =
    runTest {
      val mockRemoteSource = mockk<ICookpadRemoteSource>(relaxed = true)
      val inputData = createRecipeItemData()
      coEvery {
        mockRemoteSource.getRecipeById(any())
      } returns SafeResult.Success(inputData)

      val cookpadRepo = CookpadRepo(
        collectionsItemDataMapper = collectionsItemDataMapper,
        recipeItemDataMapper = recipeItemDataMapper,
        cookpadRemoteSource = mockRemoteSource
      )
      val result = cookpadRepo.getRecipeById(1)
      val expectedOutputData = createRecipeItemDomain()

      assert((result as SafeResult.Success<*>).data == expectedOutputData)
    }

  @Test
  fun `when remote source returns Failure, repo should also return failure`() = runTest {
    val mockRemoteSource = mockk<ICookpadRemoteSource>(relaxed = true)
    coEvery {
      mockRemoteSource.getRecipeById(any())
    } returns SafeResult.Failure(any())

    val cookpadRepo = CookpadRepo(
      collectionsItemDataMapper = collectionsItemDataMapper,
      recipeItemDataMapper = recipeItemDataMapper,
      cookpadRemoteSource = mockRemoteSource
    )
    val result = cookpadRepo.getRecipeById(1)

    assert(result is SafeResult.Failure)
  }

  @Test
  fun `when remote source returns NetworkError, repo should also return NetworkError`() = runTest {
    val mockRemoteSource = mockk<ICookpadRemoteSource>(relaxed = true)
    coEvery {
      mockRemoteSource.getRecipeById(any())
    } returns SafeResult.NetworkError

    val cookpadRepo = CookpadRepo(
      collectionsItemDataMapper = collectionsItemDataMapper,
      recipeItemDataMapper = recipeItemDataMapper,
      cookpadRemoteSource = mockRemoteSource
    )
    val result = cookpadRepo.getRecipeById(1)

    assert(result is SafeResult.NetworkError)
  }
}