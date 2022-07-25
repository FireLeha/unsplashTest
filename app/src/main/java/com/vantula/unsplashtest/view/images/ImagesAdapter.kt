package com.vantula.unsplashtest.view.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vantula.unsplashtest.R
import com.vantula.unsplashtest.databinding.FragmentImagesRecyclerItemBinding
import com.vantula.unsplashtest.model.UnsplashImagesDTO
import com.vantula.unsplashtest.viewmodel.images.ImagesClickListener

class ImagesAdapter(val listener: ImagesClickListener) :
    RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    private var topicImageData: List<UnsplashImagesDTO> = listOf()

    fun setImages(data: List<UnsplashImagesDTO>) {
        this.topicImageData = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val holder = FragmentImagesRecyclerItemBinding
            .inflate(LayoutInflater
                .from(parent.context), parent, false)
        return ImagesViewHolder(holder)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(this.topicImageData[position])
    }

    override fun getItemCount(): Int = topicImageData.size


    inner class ImagesViewHolder(
        private val binding: FragmentImagesRecyclerItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(images: UnsplashImagesDTO) {
            binding.apply {
                with(images) {
                    Glide.with(itemView)
                        .load(urls.regular)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(image)

                    image.setOnClickListener {
                        listener.onItemClick(id,urls.regular)
                    }
                }
            }
        }
    }

}