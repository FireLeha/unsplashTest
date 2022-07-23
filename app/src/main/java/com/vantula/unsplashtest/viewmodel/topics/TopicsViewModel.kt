package com.vantula.unsplashtest.viewmodel.topics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vantula.unsplashtest.model.UnsplashDTO
import com.vantula.unsplashtest.repository.RepositoryTopicsImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopicsViewModel(
    private val liveData: MutableLiveData<AppStateTopicsFragment> = MutableLiveData(),
) : ViewModel() {

    private val repositoryTopicsImpl: RepositoryTopicsImpl by lazy {
        RepositoryTopicsImpl()
    }

    fun getLiveData(): LiveData<AppStateTopicsFragment> = liveData

    fun getTopics(page: Int, perPage: Int, orderBy: String) {
        liveData.postValue(AppStateTopicsFragment.Loading(0))
        Thread {
            repositoryTopicsImpl.getTopicsFromServer(page, perPage, orderBy, callback)
        }.start()

    }


    private val callback = object: Callback<UnsplashDTO> {
        override fun onResponse(call: Call<UnsplashDTO>, response: Response<UnsplashDTO>) {
            if (response.isSuccessful){
                response.body()?.let {
                    liveData.postValue(AppStateTopicsFragment.Success(listOf(it)))
                }
            } else {
                when (response.code()) {
                    400 -> {
                        liveData.postValue(AppStateTopicsFragment.Error(error("Bad Request")))
                    }
                    401 -> {
                        liveData.postValue(AppStateTopicsFragment.Error(error("Unauthorized")))
                    }
                    403 -> {
                        liveData.postValue(AppStateTopicsFragment.Error(error("Forbidden")))
                    }
                    404 -> {
                        liveData.postValue(AppStateTopicsFragment.Error(error("Not Found")))
                    }
                    500, 503 -> {
                        liveData.postValue(AppStateTopicsFragment.Error(error("Something wrong on Unsplash servers")))
                    }
                }
            }
        }

        override fun onFailure(call: Call<UnsplashDTO>, t: Throwable) {
                liveData.postValue(AppStateTopicsFragment.Error(error("Response failed")))
        }

    }
}