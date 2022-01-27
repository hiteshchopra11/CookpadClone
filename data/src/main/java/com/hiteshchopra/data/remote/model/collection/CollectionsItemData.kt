package com.hiteshchopra.data.remote.model.collection

import com.google.gson.annotations.SerializedName
import com.hiteshchopra.data.remote.mapper.DataModel

data class CollectionsItemData(
  @SerializedName("description")
  val description: String?,
  @SerializedName("id")
  val id: Int?,
  @SerializedName("preview_image_urls")
  val previewImageUrls: List<String?>?,
  @SerializedName("recipe_count")
  val recipeCount: Int?,
  @SerializedName("title")
  val title: String?
) : DataModel()