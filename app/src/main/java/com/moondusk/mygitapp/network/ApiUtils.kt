package com.moondusk.mvvmkotlin.network

import android.util.Log
import com.moondusk.mvvmkotlin.constant.BASE_URL
import com.moondusk.mvvmkotlin.constant.BASIC_P
import com.moondusk.mvvmkotlin.constant.BASIC_U
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiUtils @Inject constructor() {

    companion object {
        private const val _timeoutRead = 30
        private const val _timeoutConnect = 30
        private const val _contentType = "Content-Type"
        private const val _contentTypeValue = "application/json"
        private const val _accept = "Accept"
        private const val _acceptValue = "application/json"
        private const val _authorization = "Authorization"
    }

    private fun headerInterceptor(token: String): Interceptor {
        return Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().also {
                    it.header(_authorization, token)
                    it.header(_accept, _acceptValue)
                }.build()
            )
        }
    }


    /*private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }*/

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        val basic = Credentials.basic(BASIC_U, BASIC_P)
        Log.d("AAAAA", basic)
        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor(basic))
            //.addInterceptor(logger)
            .connectTimeout(_timeoutConnect.toLong(), TimeUnit.SECONDS)
            .readTimeout(_timeoutRead.toLong(), TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}