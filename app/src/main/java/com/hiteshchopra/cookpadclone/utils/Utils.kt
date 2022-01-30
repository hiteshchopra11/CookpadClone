package com.hiteshchopra.cookpadclone.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Locale

object Utils {
  const val FORMAT_OUTPUT = "dd MMMM yyyy"
  const val FORMAT_INPUT = "yyyy-mm-dd"
  fun showToast(context: Context, message: String) {
    return Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }

  sealed class ViewState {
    object Loading : ViewState()
    class SuccessWithData<T>(val data: T) : ViewState()
    class Failure(val exception: Exception? = null) : ViewState()
    object NetworkError : ViewState()
  }

  fun formatDate(
    date: String?,
    inputFormat: String,
    outputFormat: String
  ): String {
    return try {
      val inputDate = SimpleDateFormat(inputFormat, Locale.getDefault()).parse(date) ?: return ""
      SimpleDateFormat(outputFormat, Locale.getDefault()).format(inputDate)
    } catch (e: Exception) {
      ""
    }
  }
}