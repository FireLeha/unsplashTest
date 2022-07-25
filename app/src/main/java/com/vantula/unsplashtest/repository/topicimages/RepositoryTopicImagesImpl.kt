package com.vantula.unsplashtest.repository.topicimages

import com.vantula.unsplashtest.BuildConfig
import com.vantula.unsplashtest.model.UnsplashImagesDTO
import com.vantula.unsplashtest.utils.App
import retrofit2.Callback

class RepositoryTopicImagesImpl : RepositoryTopicImages {
    override fun getImagesFromServer(
        id: String,
        page: Int,
        perPage: Int,
        orientation: String,
        orderBy: String,
        callback: Callback<List<UnsplashImagesDTO>>,
    ) {
        App.retrofit.getImages(id,
            BuildConfig.UNSPLASH_API_KEY,
            page,
            perPage,
            orientation,
            orderBy).enqueue(callback)
    }
}