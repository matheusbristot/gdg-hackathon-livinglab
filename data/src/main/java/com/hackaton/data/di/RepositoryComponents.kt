package com.hackaton.data.di

import com.hackaton.data.BuildConfig
import com.hackaton.data.api.ApiClient
import com.hackaton.data.api.FuckyouDataSource
import com.hackaton.data.boundaries.FirebaseReference
import com.hackaton.data.boundaries.PoliticiansRepository
import com.hackaton.data.repository.DefaultFirebaseReference
import com.hackaton.data.repository.DefaultPoliticiansRepository
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
            const val SERVER_URL = "https://politicos.olhoneles.org"
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
            bean { DefaultFirebaseReference() as FirebaseReference }
            bean { DefaultPoliticiansRepository(get()) as PoliticiansRepository  }
        }

        fun execute() = listOf(getRetrofitDependencies(), getRepositoryDependencies())
    }
}