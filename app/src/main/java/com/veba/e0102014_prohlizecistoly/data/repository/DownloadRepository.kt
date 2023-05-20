package com.veba.e0102014_prohlizecistoly.data.repository

import com.veba.e0102014_prohlizecistoly.data.exceptions.ResponseBodyException
import com.veba.e0102014_prohlizecistoly.data.exceptions.ResponseCodeException
import com.veba.e0102014_prohlizecistoly.data.exceptions.ServerConnectionException
import com.veba.e0102014_prohlizecistoly.domain.services.DownloadService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import javax.inject.Inject

class DownloadRepository @Inject constructor(
    private val httpClient: OkHttpClient
) : DownloadService {
    override suspend fun downloadFile(url: String): ByteArray = withContext(Dispatchers.IO) {
        val call = httpClient.newCall(Request.Builder().url(url).build())
        var inputStream: InputStream?
        var byteArrayOutputStream: ByteArrayOutputStream?

        try {
            val response = call.execute()

            if (response.code != HttpURLConnection.HTTP_OK) {
                throw ResponseCodeException(code = response.code)
            }

            if (response.body == null || response.body.toString().isBlank()) {
                throw ResponseBodyException(message = "The server response has a blank or null body")
            }

            inputStream = response.body!!.byteStream()
            byteArrayOutputStream = ByteArrayOutputStream()

            val buff = ByteArray(1024 * 4)
            var readFile: Int

            while (withContext(Dispatchers.IO) {
                    inputStream.use {
                        it.read(buff)
                    }
                }.also { readFile = it } != -1) {
                byteArrayOutputStream.use {
                    it.write(buff, 0, readFile)
                }
            }

            return@withContext byteArrayOutputStream.toByteArray()

        } catch (e: IOException) {
            throw ServerConnectionException(e.message)
        }
    }
}