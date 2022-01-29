package com.hiteshchopra.cookpadclone.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiteshchopra.cookpadclone.collections.ViewState.Loading
import com.hiteshchopra.domain.SafeResult.Failure
import com.hiteshchopra.domain.SafeResult.NetworkError
import com.hiteshchopra.domain.SafeResult.Success
import com.hiteshchopra.domain.usecase.GetCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsFragmentVM @Inject constructor() : ViewModel() {

  @Inject
  lateinit var getCollectionsUseCase: GetCollectionsUseCase

  /* StateFlow for publishing/observing the UI changes to Fragment */
  init {
    getCollections()
  }

  private val _viewState = MutableStateFlow<ViewState>(Loading)
  val viewState: StateFlow<ViewState> = _viewState

  private fun getCollections() {
    viewModelScope.launch {
      when (val response = getCollectionsUseCase.perform()) {
        is Failure -> _viewState.value = ViewState.Failure(response.exception)
        NetworkError -> _viewState.value = ViewState.NetworkError
        is Success -> _viewState.value = ViewState.SuccessWithData(response.data)
      }
    }
  }
}

sealed class ViewState {
  object Loading : ViewState()
  class SuccessWithData<T>(val data: T) : ViewState()
  class Failure(val exception: Exception?) : ViewState()
  object NetworkError : ViewState()
}