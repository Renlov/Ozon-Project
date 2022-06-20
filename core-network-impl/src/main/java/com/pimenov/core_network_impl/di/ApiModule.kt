package com.pimenov.core_network_impl.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pimenov.core_network_impl.data.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    fun provideGson() : Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Reusable
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create()
    }
}