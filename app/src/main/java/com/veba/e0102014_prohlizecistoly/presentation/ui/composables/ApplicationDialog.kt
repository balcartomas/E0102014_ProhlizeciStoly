package com.veba.e0102014_prohlizecistoly.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.veba.e0102014_prohlizecistoly.presentation.ui.theme.ApplicationTheme
import com.veba.e0102014_prohlizecistoly.utility.Constants

@Composable
fun ApplicationAlertDialog() {

}

@Preview(name = "Landscape", locale = "cs", device = Constants.DEVICE_LANDSCAPE, group = "cs")
@Preview(name = "Landscape", locale = "en", device = Constants.DEVICE_LANDSCAPE, group = "en")
@Preview(name = "Portrait", locale = "cs", device = Constants.DEVICE_PORTRAIT, group = "cs")
@Preview(name = "Portrait", locale = "en", device = Constants.DEVICE_PORTRAIT, group = "en")
@Composable
fun AlertDialogScreenPreview() {
    ApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ApplicationAlertDialog()
        }
    }
}