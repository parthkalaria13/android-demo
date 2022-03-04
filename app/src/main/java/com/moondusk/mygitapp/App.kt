package com.moondusk.mygitapp

import android.app.Application
import com.moondusk.mvvmkotlin.data.db.realm.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppDatabase().initRealmConfiguration(this)
    }
}