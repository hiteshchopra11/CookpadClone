package com.hiteshchopra.domain.model

import com.hiteshchopra.domain.mapper.DomainModel

data class CollectionsItemDomain(
  val description: String?,
  val id: Int?,
  val previewImageUrls: List<String?>?,
  val recipeCount: Int?,
  val title: String?
) : DomainModel()