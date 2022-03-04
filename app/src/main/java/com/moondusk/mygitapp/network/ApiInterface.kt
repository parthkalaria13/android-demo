package com.moondusk.mvvmkotlin.network

import com.moondusk.mvvmkotlin.models.agent.AgentsRequest
import com.moondusk.mvvmkotlin.models.agent.AgentsResponse
import com.moondusk.mvvmkotlin.models.auth.login.LoginRequest
import com.moondusk.mvvmkotlin.models.auth.login.LoginResponse
import com.moondusk.mvvmkotlin.models.collection.CollectionsRequest
import com.moondusk.mvvmkotlin.models.collection.CollectionsResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET




interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("agents")
    suspend fun getAgents(
        @Body body: AgentsRequest
    ): AgentsResponse

    @Headers("Content-Type: application/json")
    @POST("collections")
    suspend fun getCollections(
        @Body body: CollectionsRequest
    ): CollectionsResponse

    //--------------------------------------------

    // send data using json
    @POST("authaccount/login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    // send data using variable
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    // send data with file using variable
    @Multipart
    @POST("auth/agency_registration")
    suspend fun agency_registration(
        @Header("Authorization") authorization: String,
        @Part("agency_name") agency_name: RequestBody,
        @Part registration_doc: MultipartBody.Part?,
        @Part("firebase_token") firebase_token: RequestBody
    ): LoginResponse

    // get data
    @GET("auth/sub_categories")
    suspend fun sub_categories(
        @Header("Authorization") authorization: String,
        @Query("category_id") category_id: String
    ): LoginResponse

    // get data dynamically url
    @GET("/api/users/{id}")
    suspend fun groupList(@Path("id") groupId: Int,
                          @Query("sort") sort: String): LoginResponse

    // download file
    @Streaming
    @GET
    suspend fun download_news_papers(@Url fileUrl: String): ResponseBody
}