package com.bjelor.sportify.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitFactory {

    fun create(): Retrofit {
        // TODO: Add logging interceptor if needed
        // https://www.digitalocean.com/community/tutorials/retrofit-android-example-tutorial
        val client = OkHttpClient.Builder().build()

        return Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }
}
