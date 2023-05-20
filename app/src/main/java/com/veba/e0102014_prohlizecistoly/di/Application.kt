package com.veba.e0102014_prohlizecistoly.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application @Inject constructor() : Application()