package com.vantula.unsplashtest.repository

import com.vantula.unsplashtest.model.UnsplashDTO
import retrofit2.Callback

interface RepositoryTopics {
    fun getTopicsFromServer(page: Int, perPage: Int, orderBy: String, callback: Callback<UnsplashDTO>)
}