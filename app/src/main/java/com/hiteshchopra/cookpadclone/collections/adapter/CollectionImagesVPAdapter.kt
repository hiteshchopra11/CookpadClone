package com.hiteshchopra.cookpadclone.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionImagesVPAdapter.CollectionImagesVH
import com.hiteshchopra.cookpadclone.databinding.ItemCollectionImageBinding

class CollectionImagesVPAdapter(private val listOfImages: List<String?>?) :
  Adapter<CollectionImagesVH>() {

  inner class CollectionImagesVH(private val binding: ItemCollectionImageBinding) :
    ViewHolder(binding.root) {
    fun bind(imageUrl: String?) {
      binding.ivCollectionImage.load(imageUrl)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionImagesVH {
    return CollectionImagesVH(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_collection_image,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: CollectionImagesVH, position: Int) {
    holder.bind(listOfImages?.get(position))
  }

  override fun getItemCount(): Int = listOfImages?.size ?: 0
}