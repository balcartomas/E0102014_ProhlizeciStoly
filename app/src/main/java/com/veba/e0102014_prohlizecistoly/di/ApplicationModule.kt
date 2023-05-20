package com.veba.e0102014_prohlizecistoly.di

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.veba.e0102014_prohlizecistoly.data.data_source.remote.ApplicationApi
import com.veba.e0102014_prohlizecistoly.data.session.SessionManager
import com.veba.e0102014_prohlizecistoly.domain.session.SessionService
import com.veba.e0102014_prohlizecistoly.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(Duration.ofMillis(Constants.API_TIMEOUT))
            .writeTimeout(Duration.ofMillis(Constants.API_TIMEOUT))
            .connectTimeout(Duration.ofMillis(Constants.API_TIMEOUT))
            .build()
    }

    @Provides
    @Singleton
    fun providerMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideApplicationApi(
        moshi: Moshi, okHttpClientBuilder: OkHttpClient
    ): ApplicationApi {
        return Retrofit.Builder().baseUrl(ApplicationApi.USED_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClientBuilder).build().create()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(Constants.SharedPreferences.PREF_NAME, 0)
    }

    @Provides
    @Singleton
    fun provideSessionService(
        sharedPreferences: SharedPreferences
    ): SessionService {
        return SessionManager(sharedPreferences)
    }

}