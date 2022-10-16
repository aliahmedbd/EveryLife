package com.aliahmed.everylife.di

import com.aliahmed.everylife.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { Retrofit.Builder() }
    factory { HttpLoggingInterceptor() }
    factory { GsonConverterFactory.create() }
    factory { GsonBuilder().create() }
    factory { OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).certificatePinner(get()) }

    factory {
        val httpLoggingInterceptor: HttpLoggingInterceptor = get()
        val okHttpClientBuilder: OkHttpClient.Builder = get()
        val builder: Retrofit.Builder = get()
        val server: String = BuildConfig.BASE_URL
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }

        builder
            .baseUrl(server)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}