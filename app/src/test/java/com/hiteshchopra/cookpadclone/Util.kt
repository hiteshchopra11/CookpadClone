package com.hiteshchopra.cookpadclone

import com.hiteshchopra.cookpadclone.models.recipe.RecipeItemUIModel
import com.hiteshchopra.cookpadclone.models.recipe.RecipeStepsUIModel
import com.hiteshchopra.cookpadclone.models.recipe.RecipeUserUIModel
import com.hiteshchopra.domain.model.RecipeItemDomain
import com.hiteshchopra.domain.model.RecipeStepsDomain
import com.hiteshchopra.domain.model.RecipeUserDomain

object TestUtil {
  fun createRecipeItemDomain(): RecipeItemDomain {
    return RecipeItemDomain(
      id = 1,
      imageUrl = "Url1",
      ingredients = listOf("Ingredient1", "Ingredient2"),
      publishedAt = "",
      steps = listOf(
        RecipeStepsDomain(
          description = "Description",
          imageUrls = listOf("Url1", "Url2")
        )
      ),
      story = "Story",
      title = "Title",
      user = RecipeUserDomain(name = "Name", imageUrl = "Url")
    )
  }

  fun createRecipeItemUIModel1(): RecipeItemUIModel {
    return RecipeItemUIModel(
      id = 1,
      imageUrl = "Url1",
      ingredients = listOf("Ingredient1", "Ingredient2"),
      publishedAt = "",
      steps = listOf(
        RecipeStepsUIModel(
          description = "Description",
          imageUrls = listOf("Url1", "Url2")
        )
      ),
      story = "Story",
      title = "Title",
      user = RecipeUserUIModel(name = "Name", imageUrl = "Url")
    )
  }
}