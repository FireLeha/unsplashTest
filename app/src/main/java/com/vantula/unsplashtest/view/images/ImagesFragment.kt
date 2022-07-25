package com.vantula.unsplashtest.view.images

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vantula.unsplashtest.databinding.FragmentImagesBinding
import com.vantula.unsplashtest.utils.Constants
import com.vantula.unsplashtest.utils.Constants.ITEMS_PER_PAGE
import com.vantula.unsplashtest.utils.Constants.ORDER_PHOTOS_BY
import com.vantula.unsplashtest.utils.Constants.PAGE
import com.vantula.unsplashtest.utils.Constants.PHOTO_ORIENTATION
import com.vantula.unsplashtest.utils.Constants.TOPIC_ID_KEY
import com.vantula.unsplashtest.viewmodel.images.AppStateImagesFragment
import com.vantula.unsplashtest.viewmodel.images.ImagesViewModel

class ImagesFragment : Fragment() {


    private val imagesViewModel: ImagesViewModel by lazy {
        ViewModelProvider(this).get(ImagesViewModel::class.java)
    }

    private val adapter: ImagesAdapter by lazy {
        ImagesAdapter()
    }

    private var topicId: String = ""
    lateinit var sp: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = activity?.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)!!
        getTopicId()
        initView()
        imagesViewModel.getLiveData().observe(viewLifecycleOwner){
            renderData(it)
        }
    }

    private fun renderData(appStateImagesFragment: AppStateImagesFragment) {
        with(binding) {
            when (appStateImagesFragment) {
                is AppStateImagesFragment.Error -> {

                }
                is AppStateImagesFragment.Loading -> {
                    imagesFragmentLoadingLayout.visibility = View.VISIBLE
                }

                is AppStateImagesFragment.Success -> {
                    imagesFragmentLoadingLayout.visibility = View.GONE
                    adapter.setImages(appStateImagesFragment.imagesData)
                }
            }
        }

    }

    private fun getTopicId(){
        topicId = sp.getString(TOPIC_ID_KEY, "").toString()
    }

    private fun initView(){
        with(binding){
            imagesFragmentRecyclerView.adapter = adapter
            imagesFragmentRecyclerView.setHasFixedSize(true)
            loadImagesList()
        }
    }

    private fun loadImagesList() {
        Thread{
            imagesViewModel.getTopicImages(topicId,PAGE, ITEMS_PER_PAGE, PHOTO_ORIENTATION,
                ORDER_PHOTOS_BY)
        }.start()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = ImagesFragment()
    }

    private var _binding: FragmentImagesBinding? = null
    private val binding: FragmentImagesBinding
        get() {
            return _binding!!
        }

}