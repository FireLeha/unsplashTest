package com.vantula.unsplashtest.viewmodel.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vantula.unsplashtest.model.UnsplashImagesDTO
import com.vantula.unsplashtest.repository.topicimages.RepositoryTopicImagesImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesViewModel(
    private val liveData: MutableLiveData<AppStateImagesFragment> =MutableLiveData()
):ViewModel() {

    private val repositoryTopicImagesImpl : RepositoryTopicImagesImpl by lazy {
        RepositoryTopicImagesImpl()
    }

    fun getLiveData(): LiveData<AppStateImagesFragment> = liveData

    fun getTopicImages(id: String, page: Int, perPage: Int, orientation: String, orderBy: String) {
        liveData.postValue(AppStateImagesFragment.Loading(0))
        Thread{
            repositoryTopicImagesImpl.getImagesFromServer(id, page, perPage, orientation, orderBy, callback)
        }.start()
    }

    private val callback = object: Callback<List<UnsplashImagesDTO>> {
        override fun onResponse(
            call: Call<List<UnsplashImagesDTO>>,
            response: Response<List<UnsplashImagesDTO>>,
        ) {
            if (response.isSuccessful){
                response.body()?.let {
                    liveData.postValue(AppStateImagesFragment.Success(it))
                }
            } else {
                when (response.code()) {
                    400 -> {
                        liveData.postValue(AppStateImagesFragment.Error(error("Bad Request")))
                    }
                    401 -> {
                        liveData.postValue(AppStateImagesFragment.Error(error("Unauthorized")))
                    }
                    403 -> {
                        liveData.postValue(AppStateImagesFragment.Error(error("Forbidden")))
                    }
                    404 -> {
                        liveData.postValue(AppStateImagesFragment.Error(error("Not Found")))
                    }
                    500, 503 -> {
                        liveData.postValue(AppStateImagesFragment.Error(error("Something wrong on Unsplash servers")))
                    }
                }
            }
        }

        override fun onFailure(call: Call<List<UnsplashImagesDTO>>, t: Throwable) {
            liveData.postValue(AppStateImagesFragment.Error(error("Response failed")))
        }

    }


}