package com.veba.e0102014_prohlizecistoly.di

import com.veba.e0102014_prohlizecistoly.domain.services.DownloadService
import com.veba.e0102014_prohlizecistoly.domain.use_case.download.DownloadFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.FilesUseCaseHolder
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.DeleteFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.GetJsonFromFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.SaveFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.UnpackZipFileUC
import com.veba.e0102014_prohlizecistoly.domain.use_case.update.AppUpdaterHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HoldersModule {

    @Provides
    @Singleton
    fun provideFilesUseCaseHolder(
        saveFileUC: SaveFileUC,
        deleteFileUC: DeleteFileUC,
        getJsonFromFileUC: GetJsonFromFileUC,
        unpackZipFileUC: UnpackZipFileUC
    ): FilesUseCaseHolder {
        return FilesUseCaseHolder(saveFileUC, deleteFileUC, getJsonFromFileUC, unpackZipFileUC)
    }

    @Provides
    @Singleton
    fun provideAppUpdateHolder(
        filesUseCaseHolder: FilesUseCaseHolder,
        downloadService: DownloadService
    ): AppUpdaterHolder {
        return AppUpdaterHolder(filesUseCaseHolder, downloadService)
    }
}