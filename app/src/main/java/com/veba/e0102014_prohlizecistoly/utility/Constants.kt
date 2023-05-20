package com.veba.e0102014_prohlizecistoly.utility

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning

object Constants {

    const val DEVICE = "spec:id=reference_phone,shape=Normal,width=2560,height=1504,unit=dp,dpi=320"
    const val DEVICE_LANDSCAPE =
        "spec:id=reference_phone,shape=Normal,width=2560,height=1504,unit=dp,dpi=320"
    const val DEVICE_PORTRAIT =
        "spec:width=2560dp,height=1504dp,dpi=320,orientation=portrait"

    // Values are in ms
    const val API_TIMEOUT = 30000L

    const val DOWNLOAD_COMPLETE_ACTION = "android.intent.action.DOWNLOAD_COMPLETE"
    const val SHARED_DOWNLOAD =
        "http://nas5.veba.cz:8080/share.cgi?ssid=301968d31389402dbb10c84d25c93431&openfolder=forcedownload"
    const val SHARED_FILE = "ShareList"

    const val METADATA = "output-metadata.json"
    const val CONFIGURATION = "configuration.json"
    const val APK = "ProhlizeciStoly-release.apk"

    const val CONFIG_APK_JSON_ELEMENT = "apkPath"
    const val CONFIG_METADATA_JSON_ELEMENT = "versionPath"
    const val METADATA_ELEMENTS = "elements"
    const val METADATA_VERSION = "versionName"

    const val ROUND_CORNER = 10

    class AppIcons {
        companion object {
            val Information = Icons.Filled.Info
            val Question = Icons.Filled.HelpOutline
            val Warning = Icons.Filled.Warning
            val Error = Icons.Filled.Error
        }
    }

    class SharedPreferences {
        companion object {
            const val PREF_NAME = "APPLICATION"
            const val API_KEY = "API_KEY"
            const val PERSON_ID = "PERSON_ID"
            const val PERSON_NAME = "PERSON_NAME"
            const val ANDROID_ID = "ANDROID_ID"
            const val LOGIN_STATUS = "LOGIN_STATUS"
        }
    }

    class FileExtensions {
        companion object {
            const val ZIP = ".zip"
        }
    }

}