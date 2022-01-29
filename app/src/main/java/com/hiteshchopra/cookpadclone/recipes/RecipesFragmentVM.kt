package com.hiteshchopra.cookpadclone.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiteshchopra.cookpadclone.utils.ViewState
import com.hiteshchopra.cookpadclone.utils.ViewState.Loading
import com.hiteshchopra.domain.SafeResult.Failure
import com.hiteshchopra.domain.SafeResult.NetworkError
import com.hiteshchopra.domain.SafeResult.Success
import com.hiteshchopra.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesFragmentVM @Inject constructor() : ViewModel() {
  @Inject
  lateinit var getRecipesUseCase: GetRecipesUseCase

  /* StateFlow for publishing/observing the UI changes to Fragment */
  init {
    getRecipes()
  }

  private val _viewState = MutableStateFlow<ViewState>(Loading)
  val viewState: StateFlow<ViewState> = _viewState

  private fun getRecipes() {
    viewModelScope.launch {
      when (val response = getRecipesUseCase.perform()) {
        is Failure -> _viewState.value = ViewState.Failure(response.exception)
        NetworkError -> _viewState.value = ViewState.NetworkError
        is Success -> _viewState.value = ViewState.SuccessWithData(response.data)
      }
    }
  }
}