package com.hiteshchopra.domain.injection

import com.hiteshchopra.domain.repo.ICookpadRepo
import com.hiteshchopra.domain.usecase.GetCollectionByIdUseCase
import com.hiteshchopra.domain.usecase.GetCollectionsUseCase
import com.hiteshchopra.domain.usecase.GetRecipeByCollectionIdUseCase
import com.hiteshchopra.domain.usecase.GetRecipeByIdUseCase
import com.hiteshchopra.domain.usecase.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

  @Provides
  fun provideGetCollectionsUseCase(cookpadRepo: ICookpadRepo): GetCollectionsUseCase {
    return GetCollectionsUseCase(cookpadRepo)
  }

  @Provides
  fun provideGetCollectionByIdsUseCase(cookpadRepo: ICookpadRepo): GetCollectionByIdUseCase {
    return GetCollectionByIdUseCase(cookpadRepo)
  }

  @Provides
  fun provideGetRecipeByCollectionIdUseCase(cookpadRepo: ICookpadRepo): GetRecipeByCollectionIdUseCase {
    return GetRecipeByCollectionIdUseCase(cookpadRepo)
  }

  @Provides
  fun providesGetRecipesUseCase(cookpadRepo: ICookpadRepo): GetRecipesUseCase {
    return GetRecipesUseCase(cookpadRepo)
  }

  @Provides
  fun providesGetRecipesByIdUseCase(cookpadRepo: ICookpadRepo): GetRecipeByIdUseCase {
    return GetRecipeByIdUseCase(cookpadRepo)
  }

}