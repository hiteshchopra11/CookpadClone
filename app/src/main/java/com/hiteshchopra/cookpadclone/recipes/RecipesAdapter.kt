package com.hiteshchopra.cookpadclone.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.databinding.ItemRecipeBinding
import com.hiteshchopra.cookpadclone.recipes.RecipesAdapter.RecipeViewHolder
import com.hiteshchopra.domain.model.RecipeItemDomain

class RecipesAdapter(
  private val recipeListener: RecipeListener,
  private val recipeItemDomainList: List<RecipeItemDomain>
) : Adapter<RecipeViewHolder>() {

  inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
    ViewHolder(binding.root) {
    fun bind(recipeItemDomain: RecipeItemDomain) {
      binding.apply {
        recipe = recipeItemDomain.title
        ivRecipeImage.load(recipeItemDomain.imageUrl)
        root.setOnClickListener {
          recipeListener.onRecipeClicked(recipeItemDomain)
        }
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
    return RecipeViewHolder(
      DataBindingUtil.inflate(
        LayoutInflater.from(parent.context),
        R.layout.item_recipe,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
    holder.bind(recipeItemDomainList[position])
  }

  override fun getItemCount(): Int = recipeItemDomainList.size
}

interface RecipeListener {
  fun onRecipeClicked(recipeItemDomain: RecipeItemDomain)
}