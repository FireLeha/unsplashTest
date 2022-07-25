package com.vantula.unsplashtest.view.detailsimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vantula.unsplashtest.R
import com.vantula.unsplashtest.databinding.FragmentDetailsRecyclerItemBinding
import com.vantula.unsplashtest.model.UnsplashDetailDTO

class DetailsAdapter() : RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    private var detailsData: List<UnsplashDetailDTO> = listOf()

    fun setImages(data: List<UnsplashDetailDTO>) {
        this.detailsData = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val holder = FragmentDetailsRecyclerItemBinding
            .inflate(LayoutInflater
                .from(parent.context), parent, false)
        return DetailsViewHolder(holder)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(this.detailsData[position])
    }

    override fun getItemCount(): Int = detailsData.size


    inner class DetailsViewHolder(
        private val binding: FragmentDetailsRecyclerItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(images: UnsplashDetailDTO) {
            binding.apply {
                with(images) {
                    Glide.with(itemView)
                        .load(urls.regular)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_error)
                        .into(imageDetails)

                }
            }
        }
    }

}