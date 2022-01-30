package com.hiteshchopra.cookpadclone.models.collection

import com.hiteshchopra.domain.mapper.UIModel

data class CollectionsItemUIModel(
  val description: String?,
  val id: Int?,
  val previewImageUrls: List<String?>?,
  val recipeCount: Int?,
  val title: String?
) : UIModel()