package com.example.myapplication.data.source.remote.middleware

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

class AddHeaderInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val response = chain.proceed(builder.build())

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            LocalBroadcastManager.getInstance(context)
                .sendBroadcast(Intent(UnauthorizedBroadcast.INTENT_UNAUTHORIZED))
        }
        return response
    }
}