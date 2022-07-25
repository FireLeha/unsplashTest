package com.vantula.unsplashtest.viewmodel.details

import com.vantula.unsplashtest.model.UnsplashDetailDTO

sealed class AppStateDetailsFragment {
    data class Success  (val imagesData: List<UnsplashDetailDTO>): AppStateDetailsFragment()
    data class Error( val error:Throwable): AppStateDetailsFragment()
}