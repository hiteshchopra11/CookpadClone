package com.hiteshchopra.cookpadclone.recipes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.collections.adapter.ImagesVPAdapter
import com.hiteshchopra.cookpadclone.databinding.FragmentRecipeDetailsBinding
import com.hiteshchopra.cookpadclone.databinding.ItemStepBinding
import com.hiteshchopra.domain.model.RecipeItemDomain

class RecipeDetailsFragment : Fragment(), RecipeStepsListener {

  private val args: RecipeDetailsFragmentArgs by navArgs()

  private lateinit var binding: FragmentRecipeDetailsBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(
      inflater,
      R.layout.fragment_recipe_details,
      container,
      false
    )
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUI(args.recipe)
  }

  private fun initUI(recipe: RecipeItemDomain) {
    val ingredientsList: List<String>? =
      recipe.ingredients?.mapIndexed { index, ingredient -> "${index + 1} $ingredient" }

    var ingredientString = ""
    ingredientsList?.forEach { ingredient ->
      ingredientString += ingredient
    }
    binding.apply {
      recipeName = recipe.title
      date = recipe.publishedAt
      author = recipe.user?.name
      ivAuthor.load(recipe.user?.imageUrl)
      ivRecipeImage.load(recipe.imageUrl)
      story = recipe.story
      ingredients = ingredientString
    }
  }

  override fun populateImages(itemStepBinding: ItemStepBinding, imagesUrlList: List<String?>?) {
    val stepImagesVPAdapter = ImagesVPAdapter(imagesUrlList)
    itemStepBinding.vpRecipeStepsImages.adapter = stepImagesVPAdapter
    TabLayoutMediator(
      itemStepBinding.tabDotIndicator,
      itemStepBinding.vpRecipeStepsImages
    ) { tab, position ->
    }.attach()
  }
}