package com.vantula.unsplashtest.repository.details

import com.vantula.unsplashtest.BuildConfig
import com.vantula.unsplashtest.model.UnsplashDetailDTO
import com.vantula.unsplashtest.utils.App
import retrofit2.Callback

class RepositoryDetailsImpl: RepositoryDetails {
    override fun getDetailedImage(id: String, callback: Callback<List<UnsplashDetailDTO>>) {
        App.retrofit.getPhoto(id, BuildConfig.UNSPLASH_API_KEY).enqueue(callback)
    }
}