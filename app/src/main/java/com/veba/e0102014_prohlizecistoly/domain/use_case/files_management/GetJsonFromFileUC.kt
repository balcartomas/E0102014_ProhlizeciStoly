package com.veba.e0102014_prohlizecistoly.domain.use_case.files_management

import com.veba.e0102014_prohlizecistoly.domain.services.FileService
import org.json.JSONObject
import java.io.File
import javax.inject.Inject

class GetJsonFromFileUC @Inject constructor(
    private val fileService: FileService
) {
    suspend operator fun invoke(filePath: String) : JSONObject {
        return fileService.getJsonFromFile(File(filePath))
    }
}