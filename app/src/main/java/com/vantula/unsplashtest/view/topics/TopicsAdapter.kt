package com.vantula.unsplashtest.view.topics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vantula.unsplashtest.R
import com.vantula.unsplashtest.databinding.FragmentTopicsBinding
import com.vantula.unsplashtest.databinding.FragmentTopicsRecyclerItemBinding
import com.vantula.unsplashtest.model.UnsplashDTO
import kotlinx.android.synthetic.main.fragment_topics_recycler_item.view.*

class TopicsAdapter: RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder>() {

    private var topicsData: List<UnsplashDTO> = listOf()

    fun setTopics(data: List<UnsplashDTO>) {
        this.topicsData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val holder = FragmentTopicsRecyclerItemBinding
            .inflate(LayoutInflater
                .from(parent.context), parent, false)
        return TopicsViewHolder(holder)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        holder.bind(this.topicsData[position])
    }

    override fun getItemCount(): Int = topicsData.size


    inner class TopicsViewHolder(
        private val binding: FragmentTopicsRecyclerItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(topics: UnsplashDTO) {
            binding.apply {
                Glide.with(itemView)
                    .load(topics.coverPhoto.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(topicCoverPhoto)

                topicTitle.text = topics.title
                topicDescription.text = topics.description
                topicTotalPhotosCount.text = topics.totalPhotos.toString()
            }

        }
    }
}