package com.hiteshchopra.domain.model

import android.os.Parcelable
import com.hiteshchopra.domain.mapper.DomainModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItemDomain(
  val id: Int?,
  val imageUrl: String?,
  val ingredients: List<String?>?,
  val publishedAt: String?,
  val steps: List<RecipeStepsDomain?>?,
  val story: String?,
  val title: String?,
  val user: RecipeUserDomain?
) : DomainModel(), Parcelable

@Parcelize
data class RecipeStepsDomain(
  val description: String?,
  val imageUrls: List<String?>?
) : DomainModel(), Parcelable

@Parcelize
data class RecipeUserDomain(
  val imageUrl: String?,
  val name: String?
) : DomainModel(), Parcelable