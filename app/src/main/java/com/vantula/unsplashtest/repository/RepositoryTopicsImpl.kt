package com.vantula.unsplashtest.repository

import com.vantula.unsplashtest.BuildConfig
import com.vantula.unsplashtest.model.UnsplashDTO
import com.vantula.unsplashtest.utils.App
import retrofit2.Callback

class RepositoryTopicsImpl : RepositoryTopics {
    override fun getTopicsFromServer(page: Int, perPage: Int, orderBy: String, callback: Callback<UnsplashDTO>) {
        App.retrofit.getTopics(BuildConfig.UNSPLASH_API_KEY,page, perPage, orderBy).enqueue(callback)
    }
}