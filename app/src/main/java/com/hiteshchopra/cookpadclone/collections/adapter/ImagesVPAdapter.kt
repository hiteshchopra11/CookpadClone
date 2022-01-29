package com.hiteshchopra.cookpadclone.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.collections.adapter.ImagesVPAdapter.ImagesVH
import com.hiteshchopra.cookpadclone.databinding.ItemImageBinding

class ImagesVPAdapter(private val listOfImages: List<String?>?) :
  Adapter<ImagesVH>() {

  inner class ImagesVH(private val binding: ItemImageBinding) :
    ViewHolder(binding.root) {
    fun bind(imageUrl: String?) {
      binding.ivCollectionImage.load(imageUrl)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesVH {
    return ImagesVH(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_image,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ImagesVH, position: Int) {
    holder.bind(listOfImages?.get(position))
  }

  override fun getItemCount(): Int = listOfImages?.size ?: 0
}