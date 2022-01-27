package com.hiteshchopra.data.injection.modules

import com.hiteshchopra.data.remote.model.collection.CollectionsItemDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeItemDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeStepsDataMapper
import com.hiteshchopra.data.remote.model.recipe.RecipeUserDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

  @Provides
  fun providesCollectionItemDataMapper(): CollectionsItemDataMapper {
    return CollectionsItemDataMapper()
  }

  @Provides
  fun providesRecipeStepsDataMapper(): RecipeStepsDataMapper {
    return RecipeStepsDataMapper()
  }

  @Provides
  fun providesRecipeUserDataMapper(): RecipeUserDataMapper {
    return RecipeUserDataMapper()
  }

  @Provides
  fun providesRecipeItemDataMapper(
    recipeStepsDataMapper: RecipeStepsDataMapper,
    recipeUserDataMapper: RecipeUserDataMapper
  ): RecipeItemDataMapper {
    return RecipeItemDataMapper(recipeStepsDataMapper, recipeUserDataMapper)
  }
}