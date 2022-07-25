package com.vantula.unsplashtest.view.detailsimage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vantula.unsplashtest.databinding.FragmentDetailsBinding
import com.vantula.unsplashtest.utils.Constants
import com.vantula.unsplashtest.viewmodel.details.AppStateDetailsFragment
import com.vantula.unsplashtest.viewmodel.details.DetailsViewModel


class DetailsFragment : Fragment() {

    private val detailsViewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private val adapter: DetailsAdapter by lazy {
        DetailsAdapter()
    }

    lateinit var sp: SharedPreferences
    private var imageUrl: String = " "
    private var imageId: String = " "

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = activity?.getSharedPreferences(Constants.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)!!
        getImageIdAndUrl()
        initView()
        detailsViewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun renderData(appStateDetailsFragment: AppStateDetailsFragment) {
        when (appStateDetailsFragment) {
            is AppStateDetailsFragment.Error -> {}

            is AppStateDetailsFragment.Success -> {
                   adapter.setImages(appStateDetailsFragment.imagesData)
            }
        }
    }

    private fun getImageIdAndUrl() {
        imageUrl = sp.getString(Constants.IMAGE_URL_KEY, "").toString()
        imageId = sp.getString(Constants.IMAGE_ID_KEY, "").toString()
        Log.d("lolz", "${imageUrl} ${imageId}")

    }

    private fun initView() {
        with(binding){
            detailsFragmentRecyclerView.adapter = adapter
            detailsFragmentRecyclerView.setHasFixedSize(true)
            loadImagesList()
        }
    }

    private fun loadImagesList() {
        Thread {
            detailsViewModel.getPicture(imageId)
        }.start()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    companion object {
        fun newInstance() = DetailsFragment()
    }


}