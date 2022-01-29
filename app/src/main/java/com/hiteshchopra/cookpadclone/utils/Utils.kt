package com.hiteshchopra.cookpadclone.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String) {
  return Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
