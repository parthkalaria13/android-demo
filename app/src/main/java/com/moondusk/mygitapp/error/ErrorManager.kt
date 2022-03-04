package com.moondusk.mvvmkotlin.error

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.moondusk.mvvmkotlin.R
import com.moondusk.mvvmkotlin.utilities.snackbar
import com.moondusk.mvvmkotlin.utilities.snackbarDissmiss
import com.moondusk.mvvmkotlin.utilities.toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorManager @Inject constructor(@ApplicationContext context: Context) {

    private val appContext = context.applicationContext

    fun handleError(view: View,
                    errorCode: Int,
                    errorMessage: String = "",
                    action: (() -> Unit)? = null) {
        when (errorCode) {
            ErrorCode.DEFAULT_ERROR -> view.snackbar(if(errorMessage == "") appContext.resources.getString(R.string.error_111) else errorMessage)
            ErrorCode.NO_INTERNET_CONNECTION -> {
                view.snackbar(if(errorMessage == "") appContext.resources.getString(R.string.error_1) else errorMessage, Snackbar.LENGTH_INDEFINITE) {
                    action?.let {
                        it()
                    }
                }
            }
            ErrorCode.DATA_ERROR -> view.snackbar(if(errorMessage == "") appContext.resources.getString(R.string.error_3) else errorMessage)
            ErrorCode.NETWORK_ERROR -> view.snackbar(if(errorMessage == "") appContext.resources.getString(R.string.error_2) else errorMessage)
        }
    }

    fun clearError(view: View) {
        view.snackbarDissmiss()
    }
}