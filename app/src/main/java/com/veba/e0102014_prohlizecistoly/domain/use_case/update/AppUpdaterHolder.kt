package com.veba.e0102014_prohlizecistoly.domain.use_case.update

import android.os.Environment
import com.veba.e0102014_prohlizecistoly.BuildConfig
import com.veba.e0102014_prohlizecistoly.domain.services.DownloadService
import com.veba.e0102014_prohlizecistoly.domain.use_case.files_management.FilesUseCaseHolder
import com.veba.e0102014_prohlizecistoly.domain.use_case.update.state.UpdateState
import com.veba.e0102014_prohlizecistoly.utility.Constants
import java.io.File
import javax.inject.Inject

class AppUpdaterHolder @Inject constructor(
    private val filesUseCaseHolder: FilesUseCaseHolder,
    private val downloadRepository: DownloadService
) {
    private val downloadFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
    private lateinit var apkDownloadUrl: String
    private lateinit var metadataDownloadUrl: String

    suspend operator fun invoke() : UpdateState {
        if (checkIfUpdateIsNeeded()) {
            val byteArray = downloadRepository.downloadFile(apkDownloadUrl)
            val apkFileDest = downloadFile + File.separator + BuildConfig.VEBA_ID

            filesUseCaseHolder.saveFileUC(name = Constants.APK, dest = apkFileDest, data = byteArray)
            println("Stáhnuto - aktualizuj!")
            return UpdateState.NotUpdated
        } else {
            filesUseCaseHolder.deleteFileUC(downloadFile)
            println("Nemusíš aktualizovat!")
            return UpdateState.Updated
        }
    }

    private suspend fun checkIfUpdateIsNeeded(): Boolean {
        val configZipFilePath = downloadConfigFile()
        processConfigFile(configZipFilePath)

        val metadataFilePath = downloadMetadataFile()

        return processMetadataFile(metadataFilePath) != BuildConfig.VERSION_NAME
    }

    private suspend fun downloadConfigFile(): String {
        val byteArray = downloadRepository.downloadFile(Constants.SHARED_DOWNLOAD)
        val sharedZipFileDest = downloadFile + File.separator + BuildConfig.VEBA_ID

        filesUseCaseHolder.saveFileUC(name = Constants.SHARED_FILE + ".zip", dest = sharedZipFileDest, data = byteArray)
        return sharedZipFileDest + File.separator + Constants.SHARED_FILE + ".zip"
    }

    private suspend fun downloadMetadataFile(): String {
        val byteArray = downloadRepository.downloadFile(metadataDownloadUrl)
        val metadataFileDest = downloadFile + File.separator + BuildConfig.VEBA_ID

        filesUseCaseHolder.saveFileUC(name = Constants.METADATA, dest = metadataFileDest, data = byteArray)
        return metadataFileDest + File.separator + Constants.SHARED_FILE
    }

    private suspend fun processConfigFile(configZipFilePath: String) {
        val byteArray = filesUseCaseHolder.unpackZipFileUC(configZipFilePath)
        filesUseCaseHolder.saveFileUC(
            name = "",
            dest = downloadFile + File.separator + BuildConfig.VEBA_ID,
            data = byteArray
        )
        val configFilePath =
            downloadFile + File.separator + BuildConfig.VEBA_ID + File.separator + Constants.SHARED_FILE + File.separator + Constants.CONFIGURATION

        val configJson = filesUseCaseHolder.getJsonFromFileUC(configFilePath)
        val applicationJson = configJson.getJSONObject(BuildConfig.VEBA_ID)

        apkDownloadUrl = applicationJson.getString(Constants.CONFIG_APK_JSON_ELEMENT)
        metadataDownloadUrl = applicationJson.getString(Constants.CONFIG_METADATA_JSON_ELEMENT)
    }

    private suspend fun processMetadataFile(metadataFilePath: String): String {
        val metadataJson = filesUseCaseHolder.getJsonFromFileUC(metadataFilePath)

        val metadataElements = metadataJson.getJSONArray(Constants.METADATA_ELEMENTS)

        return metadataElements.getJSONObject(0).getString(Constants.METADATA_VERSION)
    }

}