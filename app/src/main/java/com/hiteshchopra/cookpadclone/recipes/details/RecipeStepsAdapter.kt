package com.hiteshchopra.cookpadclone.recipes.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.databinding.ItemStepBinding
import com.hiteshchopra.cookpadclone.recipes.details.RecipeStepsAdapter.RecipeStepViewHolder
import com.hiteshchopra.domain.model.RecipeStepsDomain

class RecipeStepsAdapter(
  private val recipeStepsListener: RecipeStepsListener,
  private val stepsList: List<RecipeStepsDomain>
) : Adapter<RecipeStepViewHolder>() {

  inner class RecipeStepViewHolder(val binding: ItemStepBinding) : ViewHolder(binding.root) {
    fun bind(recipeStepDomain: RecipeStepsDomain) {
      binding.tvStepDescription.text = recipeStepDomain.description
      recipeStepsListener.populateImages(binding,recipeStepDomain.imageUrls)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepViewHolder {
    return RecipeStepViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_step,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: RecipeStepViewHolder, position: Int) {
    holder.bind(stepsList[position])
  }

  override fun getItemCount(): Int = stepsList.size
}

interface RecipeStepsListener {
  fun populateImages(itemStepBinding: ItemStepBinding, imagesUrlList: List<String?>?)
}