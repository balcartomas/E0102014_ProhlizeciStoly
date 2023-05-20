package com.veba.e0102014_prohlizecistoly.domain.services

import org.json.JSONObject
import java.io.File

interface FileService {
    suspend fun saveFile(dest: String, name: String, data: ByteArray)
    suspend fun deleteFile(file: File)
    suspend fun getJsonFromFile(file: File): JSONObject
    suspend fun unpackZipFile(file: File): ByteArray
}