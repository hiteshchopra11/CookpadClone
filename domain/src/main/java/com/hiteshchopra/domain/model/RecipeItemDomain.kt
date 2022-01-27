package com.hiteshchopra.domain.model

import com.hiteshchopra.domain.mapper.DomainModel

data class RecipeItemDomain(
  val id: Int?,
  val imageUrl: String?,
  val ingredients: List<String?>?,
  val publishedAt: String?,
  val steps: List<RecipeStepsDomain?>?,
  val story: String?,
  val title: String?,
  val user: RecipeUserDomain?
) : DomainModel()

data class RecipeStepsDomain(
  val description: String?,
  val imageUrls: List<String?>?
) : DomainModel()

data class RecipeUserDomain(
  val imageUrl: String?,
  val name: String?
) : DomainModel()