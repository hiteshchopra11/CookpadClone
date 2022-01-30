package com.hiteshchopra.cookpadclone.models.recipe

import android.os.Parcelable
import com.hiteshchopra.domain.mapper.UIModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeItemUIModel(
  val id: Int?,
  val imageUrl: String?,
  val ingredients: List<String?>?,
  val publishedAt: String?,
  val steps: List<RecipeStepsUIModel?>?,
  val story: String?,
  val title: String?,
  val user: RecipeUserUIModel?
) : UIModel(), Parcelable

@Parcelize
data class RecipeStepsUIModel(
  val description: String?,
  val imageUrls: List<String?>?
) : UIModel(), Parcelable

@Parcelize
data class RecipeUserUIModel(
  val imageUrl: String?,
  val name: String?
) : UIModel(), Parcelable
