package com.veba.e0102014_prohlizecistoly.di

import com.veba.e0102014_prohlizecistoly.domain.services.FileService
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.DeleteFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.GetJsonFromFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.SaveFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.UnpackZipFileUC
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideSaveFileUseCase(
        repository: FileService
    ): SaveFileUC {
        return SaveFileUC(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteFileUseCase(
        repository: FileService
    ): DeleteFileUC {
        return DeleteFileUC(repository)
    }

    @Provides
    @Singleton
    fun provideGetJsonFromFileUseCase(
        repository: FileService
    ): GetJsonFromFileUC {
        return GetJsonFromFileUC(repository)
    }

    @Provides
    @Singleton
    fun provideUnpackZipFileUseCase(
        repository: FileService
    ): UnpackZipFileUC {
        return UnpackZipFileUC(repository)
    }
}