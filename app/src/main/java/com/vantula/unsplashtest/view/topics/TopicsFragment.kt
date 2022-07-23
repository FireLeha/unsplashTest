package com.vantula.unsplashtest.view.topics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vantula.unsplashtest.databinding.FragmentTopicsBinding
import com.vantula.unsplashtest.utils.ORDER_BY
import com.vantula.unsplashtest.utils.PAGE
import com.vantula.unsplashtest.utils.PER_PAGE
import com.vantula.unsplashtest.viewmodel.topics.AppStateTopicsFragment
import com.vantula.unsplashtest.viewmodel.topics.TopicsViewModel

class TopicsFragment : Fragment() {

    private val adapter: TopicsAdapter by lazy {
        TopicsAdapter()
    }

    private val topicsViewModel: TopicsViewModel by lazy {
        ViewModelProvider(this).get(TopicsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        topicsViewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun initView() {
        with(binding) {
            categoriesFragmentRecyclerView.adapter = adapter
            loadTopicsList()
        }

    }

    private fun loadTopicsList() {
        topicsViewModel.getTopics(PAGE, PER_PAGE, ORDER_BY)
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
}