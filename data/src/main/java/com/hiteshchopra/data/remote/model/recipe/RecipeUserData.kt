package com.hiteshchopra.data.remote.model.recipe

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.data.remote.mapper.DataModel

data class RecipeUserData(
  @SerializedName("image_url")
  val imageUrl: String?,
  @SerializedName("name")
  val name: String?
) : DataModel()