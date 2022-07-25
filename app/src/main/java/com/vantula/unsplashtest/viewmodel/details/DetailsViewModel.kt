package com.vantula.unsplashtest.viewmodel.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vantula.unsplashtest.model.UnsplashDetailDTO
import com.vantula.unsplashtest.repository.details.RepositoryDetailsImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(private val liveData: MutableLiveData<AppStateDetailsFragment> = MutableLiveData()) :
    ViewModel() {

    private val repositoryDetailsImpl: RepositoryDetailsImpl by lazy {
        RepositoryDetailsImpl()
    }

    fun getLiveData(): LiveData<AppStateDetailsFragment> = liveData

    fun getPicture(id: String) {
        Thread {
            repositoryDetailsImpl.getDetailedImage(id, callback)
        }.start()
    }

    private val callback = object : Callback<List<UnsplashDetailDTO>> {
        override fun onResponse(
            call: Call<List<UnsplashDetailDTO>>,
            response: Response<List<UnsplashDetailDTO>>,
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.postValue(AppStateDetailsFragment.Success(it))
                }
            } else {
                when (response.code()) {
                    400 -> {
                        liveData.postValue(AppStateDetailsFragment.Error(error("Bad Request")))
                    }
                    401 -> {
                        liveData.postValue(AppStateDetailsFragment.Error(error("Unauthorized")))
                    }
                    403 -> {
                        liveData.postValue(AppStateDetailsFragment.Error(error("Forbidden")))
                    }
                    404 -> {
                        liveData.postValue(AppStateDetailsFragment.Error(error("Not Found")))
                    }
                    500, 503 -> {
                        liveData.postValue(AppStateDetailsFragment.Error(error("Something wrong on Unsplash servers")))
                    }
                }
            }
        }

        override fun onFailure(call: Call<List<UnsplashDetailDTO>>, t: Throwable) {
            Log.d("callback", t.toString())
            liveData.postValue(AppStateDetailsFragment.Error(error("Response failed")))
        }

    }
}