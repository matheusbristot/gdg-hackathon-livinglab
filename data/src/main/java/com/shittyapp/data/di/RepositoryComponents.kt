package com.shittyapp.data.di

import com.shittyapp.data.BuildConfig
import com.shittyapp.data.api.ApiClient
import com.shittyapp.data.api.FuckyouDataSource
import com.shittyapp.data.boundaries.PostRepository
import com.shittyapp.data.repository.DefaultPostRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RepositoryComponents {
    companion object {
        object DatasourceProperties {
            const val SERVER_URL = "https://jsonplaceholder.typicode.com/"
        }

        inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
            val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(T::class.java)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return OkHttpClient.Builder()
                    .connectTimeout(60L, TimeUnit.SECONDS)
                    .readTimeout(60L, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
        }

        private fun getRetrofitDependencies() = applicationContext {
            bean { createOkHttpClient() }

            bean {
                createWebService<FuckyouDataSource>(get(), DatasourceProperties.SERVER_URL)
            }
        }

        private fun getRepositoryDependencies() = applicationContext {
            bean { ApiClient(get()) }
            bean { DefaultPostRepository(get()) as PostRepository}
        }

        fun execute() = listOf(getRetrofitDependencies(), getRepositoryDependencies())
    }
}