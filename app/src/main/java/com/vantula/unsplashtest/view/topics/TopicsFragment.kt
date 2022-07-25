package com.vantula.unsplashtest.view.topics

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vantula.unsplashtest.R
import com.vantula.unsplashtest.databinding.FragmentTopicsBinding
import com.vantula.unsplashtest.utils.Constants.ITEMS_PER_PAGE
import com.vantula.unsplashtest.utils.Constants.ORDER_TOPICS_BY
import com.vantula.unsplashtest.utils.Constants.PAGE
import com.vantula.unsplashtest.utils.Constants.SHARED_PREFERENCES_KEY
import com.vantula.unsplashtest.utils.Constants.TOPIC_ID_KEY
import com.vantula.unsplashtest.view.images.ImagesFragment
import com.vantula.unsplashtest.viewmodel.topics.AppStateTopicsFragment
import com.vantula.unsplashtest.viewmodel.topics.TopicsClickListener
import com.vantula.unsplashtest.viewmodel.topics.TopicsViewModel

class TopicsFragment : Fragment(), TopicsClickListener {

    private val adapter: TopicsAdapter by lazy {
        TopicsAdapter(this)
    }


    private val topicsViewModel: TopicsViewModel by lazy {
        ViewModelProvider(this).get(TopicsViewModel::class.java)
    }

    private lateinit var sp: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = activity?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)!!
        initView()
        topicsViewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun initView() {
        with(binding) {
            topicsFragmentRecyclerView.adapter = adapter
            loadTopicsList()
        }

    }

    private fun loadTopicsList() {
        Thread {
            topicsViewModel.getTopics(PAGE, ITEMS_PER_PAGE, ORDER_TOPICS_BY)
        }.start()

    }

    private fun renderData(appStateTopicsFragment: AppStateTopicsFragment) {
        with(binding) {
            when (appStateTopicsFragment) {
                is AppStateTopicsFragment.Error -> {

                }
                is AppStateTopicsFragment.Loading -> {
                    topicsFragmentLoadingLayout.visibility = View.VISIBLE
                }
                is AppStateTopicsFragment.Success -> {
                    topicsFragmentLoadingLayout.visibility = View.GONE
                    adapter.setTopics(appStateTopicsFragment.topicsData)
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTopicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = TopicsFragment()
    }

    private var _binding: FragmentTopicsBinding? = null
    private val binding: FragmentTopicsBinding
        get() {
            return _binding!!
        }

    override fun onItemClick(id: String) {
            val editor = sp.edit()
            editor?.putString(TOPIC_ID_KEY, id)
            editor?.apply()

            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ImagesFragment.newInstance())
                    .addToBackStack("").commit()
            }


    }

}