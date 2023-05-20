package com.veba.e0102014_prohlizecistoly.data.data_source.remote

interface ApplicationApi {

    companion object {
        private const val SERVER_URL = "http://appserver.veba.cz:8080"
        private const val LOCAL_URL = "http://pc043.veba.cz:20543"
        const val USED_URL = SERVER_URL
    }
}