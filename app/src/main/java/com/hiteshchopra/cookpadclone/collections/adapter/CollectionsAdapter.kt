package com.hiteshchopra.cookpadclone.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionsAdapter.CollectionsItemViewHolder
import com.hiteshchopra.cookpadclone.databinding.ItemCollectionsBinding
import com.hiteshchopra.cookpadclone.models.collection.CollectionsItemUIModel

class CollectionsAdapter(
  private val collectionsListener: CollectionsListener,
  private val collectionsItemUIModelList: List<CollectionsItemUIModel>
) : Adapter<CollectionsItemViewHolder>() {

  inner class CollectionsItemViewHolder(private val binding: ItemCollectionsBinding) :
    ViewHolder(binding.root) {
    fun bind(collectionsItemUIModel: CollectionsItemUIModel) {
      binding.title = collectionsItemUIModel.title
      binding.description = collectionsItemUIModel.description
      collectionsListener.populateImages(
        binding,
        collectionsItemUIModel.previewImageUrls
      )
      binding.root.setOnClickListener {
        collectionsListener.onCollectionItemClicked(collectionsItemUIModel)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsItemViewHolder {
    return CollectionsItemViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.item_collections, parent, false
      )
    )
  }

  override fun onBindViewHolder(holder: CollectionsItemViewHolder, position: Int) {
    return holder.bind(collectionsItemUIModel = collectionsItemUIModelList[position])
  }

  override fun getItemCount(): Int = collectionsItemUIModelList.size
}

interface CollectionsListener {
  fun populateImages(
    itemCollectionBinding: ItemCollectionsBinding,
    imagesCollection: List<String?>?
  )

  fun onCollectionItemClicked(collectionsItemUIModel: CollectionsItemUIModel)
}