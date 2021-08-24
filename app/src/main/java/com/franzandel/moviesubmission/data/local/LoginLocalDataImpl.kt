package com.franzandel.moviesubmission.data.local

import android.content.Context
import androidx.core.content.edit
import com.franzandel.moviesubmission.core.data.wrapper.Result
import com.franzandel.moviesubmission.core.data.wrapper.suspendTryCatch
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LoginLocalDataImpl @Inject constructor(@ApplicationContext private val context: Context) :
    LoginLocalData {

    private val sharedPrefs = context.getSharedPreferences(LOGIN_SHARED_PREFS, Context.MODE_PRIVATE)

    override suspend fun storeUsername(username: String): Result<Unit> =
        suspendTryCatch {
            Result.Success(
                sharedPrefs.edit {
                    putString(USERNAME, username)
                }
            )
        }

    override suspend fun getUsername(): Result<String> =
        suspendTryCatch {
            Result.Success(sharedPrefs.getString(USERNAME, "") ?: "")
        }

    companion object {
        const val LOGIN_SHARED_PREFS = "login_shared_prefs"
        const val USERNAME = "username"
    }
}