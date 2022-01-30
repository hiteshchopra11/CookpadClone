package com.hiteshchopra.cookpadclone.injection

import com.hiteshchopra.cookpadclone.models.collection.CollectionsItemUIMapper
import com.hiteshchopra.cookpadclone.models.recipe.RecipeItemUIMapper
import com.hiteshchopra.cookpadclone.models.recipe.RecipeStepsUIMapper
import com.hiteshchopra.cookpadclone.models.recipe.RecipeUserUIMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UIMapperModule {

  @Provides
  fun providesCollectionsItemUIMapper(): CollectionsItemUIMapper {
    return CollectionsItemUIMapper()
  }

  @Provides
  fun providesRecipeStepsUIMapper(): RecipeStepsUIMapper {
    return RecipeStepsUIMapper()
  }

  @Provides
  fun providesRecipeUserUIMapper(): RecipeUserUIMapper {
    return RecipeUserUIMapper()
  }

  @Provides
  fun providesRecipeItemDataMapper(
    recipeStepsDataMapper: RecipeStepsUIMapper,
    recipeUserDataMapper: RecipeUserUIMapper
  ): RecipeItemUIMapper {
    return RecipeItemUIMapper(recipeStepsDataMapper, recipeUserDataMapper)
  }
}