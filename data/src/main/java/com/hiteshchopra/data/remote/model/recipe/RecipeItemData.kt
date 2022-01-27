package com.hiteshchopra.data.remote.model.recipe

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.data.remote.mapper.DataModel

data class RecipeItemData(
  @SerializedName("id")
  val id: Int?,
  @SerializedName("image_url")
  val imageUrl: String?,
  @SerializedName("ingredients")
  val ingredients: List<String?>?,
  @SerializedName("published_at")
  val publishedAt: String?,
  @SerializedName("steps")
  val steps: List<RecipeStepsData?>?,
  @SerializedName("story")
  val story: String?,
  @SerializedName("title")
  val title: String?,
  @SerializedName("user")
  val user: RecipeUserData?
) : DataModel()
