package com.example.asssignmentsdktesttask.di.modules

import com.example.asssignmentsdktesttask.BuildConfig
import com.example.asssignmentsdktesttask.data.network.deserializers.LatestRateResponseDeserializer
import com.example.asssignmentsdktesttask.data.network.deserializers.SupportedSymbolsDeserializer
import com.example.asssignmentsdktesttask.data.network.interceptor.ApiKeyInterceptor
import com.example.asssignmentsdktesttask.data.network.response.SupportedSymbolsResponse
import com.example.asssignmentsdktesttask.data.network.service.FixerCurrencyService
import com.example.asssignmentsdktesttask.di.annotation.BaseUrl
import com.example.asssignmentsdktesttask.utils.FlowCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String {
        return BuildConfig.FIXER_BASE_URL
    }

    @Provides
    fun provideGson(
        supportedSymbolsDeserializer: SupportedSymbolsDeserializer,
        latestRateResponseDeserializer: LatestRateResponseDeserializer
    ): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(
            SupportedSymbolsResponse::class.java,
            supportedSymbolsDeserializer
        )
        gsonBuilder.registerTypeAdapter(
            LatestRateResponseDeserializer::class.java,
            latestRateResponseDeserializer
        )
        return gsonBuilder.create()
    }

    @Provides
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideFixerCurrencyService(retrofit: Retrofit): FixerCurrencyService {
        return retrofit.create(FixerCurrencyService::class.java)
    }
}