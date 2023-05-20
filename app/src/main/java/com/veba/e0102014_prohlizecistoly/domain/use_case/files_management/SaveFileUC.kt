package com.veba.e0102014_prohlizecistoly.domain.use_case.files_management

import com.veba.e0102014_prohlizecistoly.domain.services.FileService
import javax.inject.Inject

class SaveFileUC @Inject constructor(
    private val fileService: FileService
) {
    suspend operator fun invoke(dest: String, name: String, data: ByteArray) {
        fileService.saveFile(dest, name, data)
    }
}