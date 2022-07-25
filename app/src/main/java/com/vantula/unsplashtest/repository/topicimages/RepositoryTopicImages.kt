package com.vantula.unsplashtest.repository.topicimages

import com.vantula.unsplashtest.model.UnsplashImagesDTO
import retrofit2.Callback

interface RepositoryTopicImages {
    fun getImagesFromServer(id: String, page: Int, perPage: Int, orientation: String, orderBy: String, callback: Callback<List<UnsplashImagesDTO>>)
}