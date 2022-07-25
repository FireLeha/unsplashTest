package com.vantula.unsplashtest.utils

import android.app.Application
import com.google.gson.GsonBuilder
import com.vantula.unsplashtest.repository.UnsplashApi
import com.vantula.unsplashtest.utils.Constants.UNSPLASH_API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    companion object {

        val retrofit = Retrofit.Builder()
            .baseUrl(UNSPLASH_API_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build().create(UnsplashApi::class.java)

    }

}