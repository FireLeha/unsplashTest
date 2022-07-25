package com.vantula.unsplashtest.viewmodel.images

import com.vantula.unsplashtest.model.UnsplashImagesDTO

sealed class AppStateImagesFragment {
    data class Loading(val progress:Int):AppStateImagesFragment()
    data class Success  (val imagesData: List<UnsplashImagesDTO>):AppStateImagesFragment()
    data class Error( val error:Throwable):AppStateImagesFragment()
}
