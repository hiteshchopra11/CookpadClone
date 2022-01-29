package com.hiteshchopra.cookpadclone.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String) {
  return Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

sealed class ViewState {
  object Loading : ViewState()
  class SuccessWithData<T>(val data: T) : ViewState()
  class Failure(val exception: Exception?) : ViewState()
  object NetworkError : ViewState()
}
