package com.hiteshchopra.data.injection.modules

import com.hiteshchopra.data.AppConstants
import com.hiteshchopra.data.remote.CookpadApiService
import com.hiteshchopra.data.remote.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  fun provideHttpClient(): OkHttpClient {
    return RetrofitHelper.createOkHttpClient()
  }

  @Provides
  fun provideRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, AppConstants.BASE_URL)
  }

  @Provides
  fun provideCookpadApiService(retrofit: Retrofit): CookpadApiService {
    return CookpadApiService.createRetrofitService(retrofit)
  }

}