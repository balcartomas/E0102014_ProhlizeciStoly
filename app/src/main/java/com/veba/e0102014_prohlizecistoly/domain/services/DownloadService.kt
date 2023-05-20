package com.veba.e0102014_prohlizecistoly.domain.services

interface DownloadService {
    suspend fun downloadFile(url: String) : ByteArray
}