package com.vantula.unsplashtest.repository.topics

import com.vantula.unsplashtest.BuildConfig.UNSPLASH_API_KEY
import com.vantula.unsplashtest.model.UnsplashTopicsDTO
import com.vantula.unsplashtest.utils.App
import retrofit2.Callback

class RepositoryTopicsImpl : RepositoryTopics {
    override fun getTopicsFromServer(page: Int, perPage: Int, orderBy: String, callback: Callback<List<UnsplashTopicsDTO>>) {
        App.retrofit.getTopics(UNSPLASH_API_KEY,page, perPage, orderBy).enqueue(callback)
    }
}