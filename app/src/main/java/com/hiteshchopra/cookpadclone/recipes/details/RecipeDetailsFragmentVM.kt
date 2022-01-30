package com.hiteshchopra.cookpadclone.recipes.details

import androidx.lifecycle.ViewModel
import com.hiteshchopra.cookpadclone.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsFragmentVM @Inject constructor() : ViewModel() {
  fun formatDate(publishedDate: String): String {
    return Utils.formatDate(
      date = publishedDate,
      inputFormat = Utils.FORMAT_INPUT,
      outputFormat = Utils.FORMAT_OUTPUT
    )
  }
}