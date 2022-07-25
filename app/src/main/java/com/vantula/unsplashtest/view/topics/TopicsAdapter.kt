package com.vantula.unsplashtest.view.topics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vantula.unsplashtest.R
import com.vantula.unsplashtest.databinding.FragmentTopicsRecyclerItemBinding
import com.vantula.unsplashtest.model.UnsplashTopicsDTO
import com.vantula.unsplashtest.viewmodel.topics.OnMyItemClickListener

class TopicsAdapter(
    val listener: OnMyItemClickListener,
) : RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder>() {

    private var topicsData: List<UnsplashTopicsDTO> = listOf()

    fun setTopics(data: List<UnsplashTopicsDTO>) {
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
        private val binding: FragmentTopicsRecyclerItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = topicsData[position].id
                    listener.onItemClick(item)
                }
            }
        }

        fun bind(topics: UnsplashTopicsDTO) {
            binding.apply {
                with(topics) {
                    Glide.with(itemView)
                        .load(coverPhoto.urls.regular)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(topicCoverPhoto)

                    topicId.text = id
                    topicTitle.text = title
                    topicDescription.text = description
                    topicTotalPhotosCount.text = "${totalPhotos.toString()} contributions"
                }
            }
        }

    }


}