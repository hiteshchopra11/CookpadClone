package com.hiteshchopra.cookpadclone.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.Loading
import com.hiteshchopra.domain.SafeResult.Failure
import com.hiteshchopra.domain.SafeResult.NetworkError
import com.hiteshchopra.domain.SafeResult.Success
import com.hiteshchopra.domain.usecase.GetRecipeByCollectionIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionRecipeFragmentVM @Inject constructor() : ViewModel() {
  @Inject
  lateinit var getRecipeByCollectionIdUseCase: GetRecipeByCollectionIdUseCase

  /* StateFlow for publishing/observing the UI changes to Fragment */
  private val _viewState = MutableStateFlow<ViewState>(Loading)
  val viewState: StateFlow<ViewState> = _viewState

  fun getRecipesByCollectionID(id: Int) {
    if (id == -1) {
      _viewState.value = ViewState.Failure()
    }
    viewModelScope.launch {
      when (val response = getRecipeByCollectionIdUseCase.perform(id)) {
        is Failure -> _viewState.value = ViewState.Failure(response.exception)
        NetworkError -> _viewState.value = ViewState.NetworkError
        is Success -> _viewState.value = ViewState.SuccessWithData(response.data)
      }
    }
  }
}