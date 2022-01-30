package com.hiteshchopra.domain

import com.hiteshchopra.domain.repo.ICookpadRepo
import com.hiteshchopra.domain.usecase.GetRecipeByCollectionIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.ArgumentMatchers.any

@ExperimentalCoroutinesApi
class GetRecipeByCollectionIdUseCaseTest {

  @Test
  fun `when repository returns success, UseCase should return success`() = runTest {
    val mockCookpadRepo = mockk<ICookpadRepo>()

    coEvery {
      mockCookpadRepo.getCollectionRecipes(any())
    } returns SafeResult.Success(any())

    val recipeByCollectionIdUseCase = GetRecipeByCollectionIdUseCase(mockCookpadRepo)

    val result = recipeByCollectionIdUseCase.perform(1)
    assert(result is SafeResult.Success<*>)
  }

  @Test
  fun `when repository returns failure, UseCase should return failure`() = runTest {
    val mockCookpadRepo = mockk<ICookpadRepo>()

    coEvery {
      mockCookpadRepo.getCollectionRecipes(any())
    } returns SafeResult.Failure(any())

    val recipeByCollectionIdUseCase = GetRecipeByCollectionIdUseCase(mockCookpadRepo)

    val result = recipeByCollectionIdUseCase.perform(1)
    assert(result is SafeResult.Failure)
  }

  @Test
  fun `when repository returns NetworkError, UseCase should return NetworkError`() = runTest {
    val mockCookpadRepo = mockk<ICookpadRepo>()

    coEvery {
      mockCookpadRepo.getCollectionRecipes(any())
    } returns SafeResult.NetworkError

    val recipeByCollectionIdUseCase = GetRecipeByCollectionIdUseCase(mockCookpadRepo)

    val result = recipeByCollectionIdUseCase.perform(1)
    assert(result is SafeResult.NetworkError)
  }

  @Test
  fun `when repository returns Success, UseCase should not return NetworkError`() = runTest {
    val mockCookpadRepo = mockk<ICookpadRepo>()

    coEvery {
      mockCookpadRepo.getCollectionRecipes(any())
    } returns SafeResult.Success(any())

    val recipeByCollectionIdUseCase = GetRecipeByCollectionIdUseCase(mockCookpadRepo)

    val result = recipeByCollectionIdUseCase.perform(1)
    assert(result !is SafeResult.NetworkError)
  }
}