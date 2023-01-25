package com.example.exam25a.repository

import com.example.exam25a.domain.MyEntity
import com.example.exam25a.domain.MyId
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


object MyEntityApi {

    private const val URL = "http://10.0.2.2:2018"

    interface MyEntityService {
        @GET("/exams")
        suspend fun getAll(): List<MyEntity>

        @GET("/draft")
        suspend fun getDraft(): List<MyEntity>

        @POST("/exam")
        suspend fun save(@Body entity: MyEntity): Response<com.example.exam25a.dto.Result>

        @POST("/join")
        suspend fun join(@Body entity: MyId): Response<com.example.exam25a.dto.Result>

        @GET("/exam/{id}")
        suspend fun getOne(@Path("id") id: Int): MyEntity

        @GET("/group/{group}")
        suspend fun getGroup(@Path("group") group: String): List<MyEntity>
    }

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    val entityService: MyEntityService = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client)
        .build()
        .create(MyEntityService::class.java)
}