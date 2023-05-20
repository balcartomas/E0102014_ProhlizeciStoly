package com.veba.e0102014_prohlizecistoly.data.repository

import com.veba.e0102014_prohlizecistoly.data.exceptions.FormatException
import com.veba.e0102014_prohlizecistoly.domain.services.FileService
import com.veba.e0102014_prohlizecistoly.utility.Constants
import com.veba.e0102014_prohlizecistoly.utility.Functions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.lang.StringBuilder
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import javax.inject.Singleton

@Singleton
class FileRepository : FileService {

    override suspend fun saveFile(dest: String, name: String, data: ByteArray) {
        var fileOutputStream: FileOutputStream?
        val file = File(dest)
        if (file.exists()) {
            deleteFile(file)
        }
        file.mkdirs()

        withContext(Dispatchers.IO) {
            val fileToSave = File(dest + File.separator + name)
            fileOutputStream = FileOutputStream(fileToSave)
            fileOutputStream.use {
                it!!.write(data)
            }
        }
    }

    override suspend fun deleteFile(file: File) {
        if (!file.exists()) {
            return
        }

        if (!file.isDirectory) {
            file.delete()
        }

        val dirContent = file.list() ?: return

        for (dirFileName: String in dirContent) {
            val dirFile = File(file, dirFileName)

            if (dirFile.exists()) {
                deleteFile(dirFile)
            }
        }
    }

    override suspend fun getJsonFromFile(file: File): JSONObject = withContext(Dispatchers.IO) {
        val stringBuilder = StringBuilder()

        val bufferedReader = BufferedReader(FileReader(file))
        var line: String?

        while (bufferedReader.use {
                readLine()
            }.also { line = it } != null) {
            stringBuilder.append("$line\n")
        }

        return@withContext if (Functions.isStringJson(stringBuilder.toString())) JSONObject(stringBuilder.toString())
        else throw FormatException(format = "JSON")
    }

    override suspend fun unpackZipFile(file: File): ByteArray = withContext(Dispatchers.IO) {
        if (file.extension != Constants.FileExtensions.ZIP) {
            throw FormatException(format = "ZIP")
        }

        val zipFile = ZipFile(file)
        val zipEntries = zipFile.entries()
        var zipEntry: ZipEntry
        val buffer = ByteArray(1024)
        val byteArrayOutputStream = ByteArrayOutputStream()

        while (zipEntries.hasMoreElements()) {
            zipEntry = zipEntries.nextElement()

            val inputStream = zipFile.getInputStream(zipEntry)

            var readFile: Int
            while (inputStream.read(buffer).also { readFile = it } != 1) {
                byteArrayOutputStream.use { it.write(buffer, 0, readFile) }
            }
        }

        return@withContext byteArrayOutputStream.toByteArray()
    }
}