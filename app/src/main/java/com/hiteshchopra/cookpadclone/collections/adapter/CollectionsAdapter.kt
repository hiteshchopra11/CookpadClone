package com.hiteshchopra.cookpadclone.collections.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionsAdapter.CollectionsItemViewHolder
import com.hiteshchopra.cookpadclone.databinding.ItemCollectionsBinding
import com.hiteshchopra.domain.model.CollectionsItemDomain

class CollectionsAdapter(
  private val collectionsListener: CollectionsListener,
  private val collectionsItemDomain: List<CollectionsItemDomain>
) : Adapter<CollectionsItemViewHolder>() {

  inner class CollectionsItemViewHolder(private val binding: ItemCollectionsBinding) :
    ViewHolder(binding.root) {
    fun bind(collectionsItemDomain: CollectionsItemDomain) {
      binding.title = collectionsItemDomain.title
      binding.description = collectionsItemDomain.description
      collectionsListener.populateImages(
        binding,
        collectionsItemDomain.previewImageUrls
      )
      binding.root.setOnClickListener {
        collectionsListener.onCollectionItemClicked(collectionsItemDomain)
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
    return holder.bind(collectionsItemDomain = collectionsItemDomain[position])
  }

  override fun getItemCount(): Int = collectionsItemDomain.size
}

interface CollectionsListener {
  fun populateImages(
    itemCollectionBinding: ItemCollectionsBinding,
    imagesCollection: List<String?>?
  )

  fun onCollectionItemClicked(collectionsItemDomain: CollectionsItemDomain)
}