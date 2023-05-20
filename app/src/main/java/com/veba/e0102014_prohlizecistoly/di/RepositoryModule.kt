package com.veba.e0102014_prohlizecistoly.di

import com.veba.e0102014_prohlizecistoly.data.repository.DownloadRepository
import com.veba.e0102014_prohlizecistoly.data.repository.FileRepository
import com.veba.e0102014_prohlizecistoly.domain.services.DownloadService
import com.veba.e0102014_prohlizecistoly.domain.services.FileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDownloadRepository(
        okHttpClient: OkHttpClient
    ): DownloadService {
        return DownloadRepository(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideFileRepository(
    ): FileService {
        return FileRepository()
    }
}