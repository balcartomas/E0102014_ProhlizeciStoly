package com.veba.e0102014_prohlizecistoly.utility

import org.json.JSONException
import org.json.JSONObject

object Functions {

    fun isStringJson(testedString: String): Boolean {
        return try {
            JSONObject(testedString)
            true
        } catch (e: JSONException) {
            false
        }
    }
}