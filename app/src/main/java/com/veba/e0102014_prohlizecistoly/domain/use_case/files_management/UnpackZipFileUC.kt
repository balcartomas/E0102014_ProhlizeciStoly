package com.veba.e0102014_prohlizecistoly.domain.use_case.files_management

import com.veba.e0102014_prohlizecistoly.domain.services.FileService
import java.io.File
import javax.inject.Inject

class UnpackZipFileUC @Inject
constructor(
    private val fileService: FileService,
) {
    suspend operator fun invoke(filePath: String): ByteArray {
        return fileService.unpackZipFile(File(filePath))
    }
}