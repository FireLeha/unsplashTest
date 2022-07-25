package com.vantula.unsplashtest.viewmodel.topics

import com.vantula.unsplashtest.model.UnsplashTopicsDTO

sealed class AppStateTopicsFragment {
    data class Loading(val progress:Int):AppStateTopicsFragment()
    data class Success  (val topicsData: List<UnsplashTopicsDTO>):AppStateTopicsFragment()
    data class Error( val error:Throwable):AppStateTopicsFragment()

}