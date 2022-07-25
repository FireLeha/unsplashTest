package com.vantula.unsplashtest.repository.details

import com.vantula.unsplashtest.model.UnsplashDetailDTO
import retrofit2.Callback

interface RepositoryDetails {
    fun getDetailedImage(id: String, callback: Callback<List<UnsplashDetailDTO>>)
}