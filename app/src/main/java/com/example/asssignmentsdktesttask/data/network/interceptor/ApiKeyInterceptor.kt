package com.example.asssignmentsdktesttask.data.network.interceptor

import com.example.asssignmentsdktesttask.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("access_key", BuildConfig.FIXER_ACCESS_KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}
