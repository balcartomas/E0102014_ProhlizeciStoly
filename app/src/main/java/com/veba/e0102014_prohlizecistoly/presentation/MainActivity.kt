package com.veba.e0102014_prohlizecistoly.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.veba.e0102014_prohlizecistoly.data.session.SessionManager
import com.veba.e0102014_prohlizecistoly.presentation.ui.theme.ApplicationTheme
import com.veba.e0102014_prohlizecistoly.utility.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveAndroidId()
        setContent {
            ApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                }
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun saveAndroidId() {
        SessionManager(this.getSharedPreferences(Constants.SharedPreferences.PREF_NAME, 0))
            .saveAndroidId(Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))

        Log.e("ANDROID_ID", Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))
    }

}

@Preview(name = "Landscape", locale = "cs", device = Constants.DEVICE_LANDSCAPE, group = "cs")
@Preview(name = "Landscape", locale = "en", device = Constants.DEVICE_LANDSCAPE, group = "en")
@Preview(name = "Portrait", locale = "cs", device = Constants.DEVICE_PORTRAIT, group = "cs")
@Preview(name = "Portrait", locale = "en", device = Constants.DEVICE_PORTRAIT, group = "en")
@Composable
fun MainScreenPreview() {
    ApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

        }
    }
}