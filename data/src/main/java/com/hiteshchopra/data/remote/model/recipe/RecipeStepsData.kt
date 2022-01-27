package com.hiteshchopra.data.remote.model.recipe

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.data.remote.mapper.DataModel

data class RecipeStepsData(
  @SerializedName("description")
  val description: String?,
  @SerializedName("image_urls")
  val imageUrls: List<String?>?
) : DataModel()
