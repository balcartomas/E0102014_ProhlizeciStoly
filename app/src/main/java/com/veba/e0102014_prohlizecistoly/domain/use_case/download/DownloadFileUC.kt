package com.veba.e0102014_prohlizecistoly.domain.use_case.download

import com.veba.e0102014_prohlizecistoly.domain.services.DownloadService
import javax.inject.Inject

class DownloadFileUC @Inject constructor(
    private val downloadService: DownloadService,
) {
    suspend operator fun invoke(url: String): ByteArray {
        return downloadService.downloadFile(url)
    }
}