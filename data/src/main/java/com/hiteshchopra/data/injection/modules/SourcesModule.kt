package com.hiteshchopra.data.injection.modules

import com.hiteshchopra.data.remote.CookpadApiService
import com.hiteshchopra.data.remote.source.CookpadRemoteSource
import com.hiteshchopra.data.remote.source.ICookpadRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

  @Provides
  fun providesCookpadRemoteSource(cookpadApiService: CookpadApiService): ICookpadRemoteSource {
    return CookpadRemoteSource(
      cookpadApiService
    )
  }
}