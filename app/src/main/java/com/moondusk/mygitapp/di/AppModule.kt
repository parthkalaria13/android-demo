package com.moondusk.mvvmkotlin.di

import android.content.Context
import com.moondusk.mvvmkotlin.data.db.room.AppDatabase
import com.moondusk.mvvmkotlin.data.db.room.StudentDao
import com.moondusk.mvvmkotlin.network.ApiInterface
import com.moondusk.mvvmkotlin.network.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInterface(apiUtils : ApiUtils) : ApiInterface {
        return apiUtils.buildApi(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideStudentDao(appDatabase: AppDatabase) : StudentDao {
        return appDatabase.studentDao()
    }
}