package com.hiteshchopra.data

import com.hiteshchopra.data.remote.model.recipe.RecipeItemData
import com.hiteshchopra.data.remote.model.recipe.RecipeStepsData
import com.hiteshchopra.data.remote.model.recipe.RecipeUserData
import com.hiteshchopra.domain.model.RecipeItemDomain
import com.hiteshchopra.domain.model.RecipeStepsDomain
import com.hiteshchopra.domain.model.RecipeUserDomain

object TestUtil {
  fun createRecipeItemData(): RecipeItemData {
    return RecipeItemData(
      id = 1,
      imageUrl = "Url1",
      ingredients = listOf("Ingredient1", "Ingredient2"),
      publishedAt = "",
      steps = listOf(
        RecipeStepsData(
          description = "Description",
          imageUrls = listOf("Url1", "Url2")
        )
      ),
      story = "Story",
      title = "Title",
      user = RecipeUserData(name = "Name", imageUrl = "Url")
    )
  }

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
}