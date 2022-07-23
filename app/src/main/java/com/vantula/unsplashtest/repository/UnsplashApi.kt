package com.vantula.unsplashtest.repository

import com.vantula.unsplashtest.model.UnsplashDTO
import com.vantula.unsplashtest.utils.UNSPLASH_API_KEY
import com.vantula.unsplashtest.utils.UNSPLASH_TOPICS_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Accept-Version: v1")
    @GET(UNSPLASH_TOPICS_ENDPOINT)
    fun getTopics(
        @Header("Authorization: Client-ID $UNSPLASH_API_KEY") apikey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String
    ): Call<UnsplashDTO>
}