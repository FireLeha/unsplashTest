package com.vantula.unsplashtest.viewmodel.topics

import com.vantula.unsplashtest.model.UnsplashDTO

sealed class AppStateTopicsFragment {
    data class Loading(val progress:Int):AppStateTopicsFragment()
    data class Success  (val topicsData: List<UnsplashDTO>):AppStateTopicsFragment()
    data class Error( val error:Throwable):AppStateTopicsFragment()

}