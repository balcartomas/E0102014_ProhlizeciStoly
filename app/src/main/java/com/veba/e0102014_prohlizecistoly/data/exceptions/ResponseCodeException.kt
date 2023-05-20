package com.veba.e0102014_prohlizecistoly.data.exceptions

class ResponseCodeException(
    code: Int? = null
) : Exception("The server request returned the code: $code")