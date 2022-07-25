package com.vantula.unsplashtest.repository.topics

import com.vantula.unsplashtest.model.UnsplashTopicsDTO
import retrofit2.Callback

interface RepositoryTopics {
    fun getTopicsFromServer(page: Int, perPage: Int, orderBy: String, callback: Callback<List<UnsplashTopicsDTO>>)
}