package com.veba.e0102014_prohlizecistoly.presentation.login

import androidx.lifecycle.ViewModel
import com.veba.e0102014_prohlizecistoly.domain.session.SessionService
import com.veba.e0102014_prohlizecistoly.domain.use_case.update.AppUpdaterHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appUpdaterHolder: AppUpdaterHolder,
    private val sessionManager: SessionService
) : ViewModel() {

}