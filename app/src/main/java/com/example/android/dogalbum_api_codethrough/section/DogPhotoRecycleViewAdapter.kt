package com.example.android.dogalbum_api_codethrough.section

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.dogalbum_api_codethrough.databinding.FragmentDogPhotoLayoutBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 */
class DogPhotoRecycleViewAdapter(
    private val values: List<String>,
) : RecyclerView.Adapter<DogPhotoRecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentDogPhotoLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentDogPhotoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            private val dogImageView = binding.imageView

        fun bind(dogPhoto: String){
            dogImageView.load(dogPhoto)
        }
    }

}