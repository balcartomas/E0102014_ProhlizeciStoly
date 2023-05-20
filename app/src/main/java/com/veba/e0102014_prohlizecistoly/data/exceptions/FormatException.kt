package com.veba.e0102014_prohlizecistoly.data.exceptions

class FormatException(
    format: String? = null
) : Exception("The given string is not in the required format ($format)")