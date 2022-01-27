package com.hiteshchopra.data.injection.modules

import com.hiteshchopra.data.remote.model.collection.CollectionsItemDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeItemDataMapper
import com.hiteshchopra.data.remote.repo.CookpadRepo
import com.hiteshchopra.data.remote.source.ICookpadRemoteSource
import com.hiteshchopra.domain.repo.ICookpadRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  fun providesCookpadRepo(
    cookpadRemoteSource: ICookpadRemoteSource,
    collectionsItemDataMapper: CollectionsItemDataMapper,
    recipeItemDataMapper: RecipeItemDataMapper
  ): ICookpadRepo {
    return CookpadRepo(cookpadRemoteSource, collectionsItemDataMapper, recipeItemDataMapper)
  }
}