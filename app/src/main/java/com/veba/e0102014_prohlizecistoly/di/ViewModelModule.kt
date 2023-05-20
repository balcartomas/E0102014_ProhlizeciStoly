package com.veba.e0102014_prohlizecistoly.di

import androidx.lifecycle.ViewModel
import com.veba.e0102014_prohlizecistoly.domain.session.SessionService
import com.veba.e0102014_prohlizecistoly.domain.use_case.update.AppUpdaterHolder
import com.veba.e0102014_prohlizecistoly.presentation.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideLoginViewModel(
        appUpdaterHolder: AppUpdaterHolder,
        sessionService: SessionService
    ): ViewModel {
        return LoginViewModel(appUpdaterHolder, sessionService)
    }
}