package com.hiteshchopra.cookpadclone

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient

@HiltAndroidApp
class CookpadApplication : Application(), ImageLoaderFactory {
  override fun newImageLoader(): ImageLoader {
    return ImageLoader.Builder(applicationContext)
      .crossfade(true)
      .placeholder(R.drawable.ic_placeholder)
      .build()
  }
}