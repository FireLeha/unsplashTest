package com.vantula.unsplashtest.repository

import com.vantula.unsplashtest.model.UnsplashDetailDTO
import com.vantula.unsplashtest.model.UnsplashImagesDTO
import com.vantula.unsplashtest.model.UnsplashTopicsDTO
import com.vantula.unsplashtest.utils.Constants.UNSPLASH_TOPICS_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {


    @Headers("Accept-Version: v1")
    @GET(UNSPLASH_TOPICS_ENDPOINT)
    fun getTopics(
        @Query("client_id") apikey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("order_by") orderBy: String,
    ): Call<List<UnsplashTopicsDTO>>

    @GET("topics/{id}/photos")
    fun getImages(
        @Path("id") id: String,
        @Query("client_id") apikey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("orientation") orientation: String,
        @Query("order_by") orderBy: String,
    ): Call<List<UnsplashImagesDTO>>

    @GET("photos/{id}")
    fun getPhoto(
        @Path("id") id: String,
        @Query("client_id") apikey: String
    ): Call<List<UnsplashDetailDTO>>
}