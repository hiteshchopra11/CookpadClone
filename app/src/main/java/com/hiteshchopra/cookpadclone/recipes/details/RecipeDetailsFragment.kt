package com.hiteshchopra.cookpadclone.recipes.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.tabs.TabLayoutMediator
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.R.string
import com.hiteshchopra.cookpadclone.base.BaseFragment
import com.hiteshchopra.cookpadclone.collections.adapter.ImagesVPAdapter
import com.hiteshchopra.cookpadclone.databinding.FragmentRecipeDetailsBinding
import com.hiteshchopra.cookpadclone.databinding.ItemStepBinding
import com.hiteshchopra.cookpadclone.utils.Utils.FORMAT_INPUT
import com.hiteshchopra.cookpadclone.utils.Utils.FORMAT_OUTPUT
import com.hiteshchopra.cookpadclone.utils.Utils.formatDate
import com.hiteshchopra.domain.model.RecipeItemDomain

class RecipeDetailsFragment : BaseFragment<FragmentRecipeDetailsBinding>(), RecipeStepsListener {

  override fun layoutId(): Int = R.layout.fragment_recipe_details

  private val args: RecipeDetailsFragmentArgs by navArgs()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUI(args.recipe)
  }

  private fun initUI(recipe: RecipeItemDomain) {
    /* Setup the toolbar */
    setupToolbar(binding.toolbar, getString(string.recipe_details), true)
    val recipeStepsAdapter = RecipeStepsAdapter(this, recipe.steps)
    val ingredientsList: List<String>? =
      recipe.ingredients?.mapIndexed { index, ingredient -> "${index + 1}.) $ingredient" }

    var ingredientString = ""
    ingredientsList?.forEach { ingredient ->
      ingredientString += ingredient + "\n"
    }
    binding.apply {
      rvStepsDescription.apply {
        adapter = recipeStepsAdapter
        isNestedScrollingEnabled = false
      }
      recipeName = recipe.title
      date = formatDate(
        date = recipe.publishedAt?.substring(0, 10),
        inputFormat = FORMAT_INPUT,
        outputFormat = FORMAT_OUTPUT
      )
      author = recipe.user?.name
      ivAuthor.load(recipe.user?.imageUrl) {
        // Coil transformation to make the image round
        transformations(RoundedCornersTransformation(50f))
      }
      ivRecipeImage.load(recipe.imageUrl)
      story = recipe.story
      ingredients = ingredientString
    }
  }

  override fun populateImages(itemStepBinding: ItemStepBinding, imagesUrlList: List<String?>?) {
    val stepImagesVPAdapter = ImagesVPAdapter(imagesUrlList)
    itemStepBinding.vpRecipeStepsImages.adapter = stepImagesVPAdapter
    if (imagesUrlList != null && imagesUrlList.size >= 2) {
      itemStepBinding.vpRecipeStepsImages.visibility = View.VISIBLE
      itemStepBinding.tabDotIndicator.visibility = View.VISIBLE
      TabLayoutMediator(
        itemStepBinding.tabDotIndicator,
        itemStepBinding.vpRecipeStepsImages
      ) { tab, position ->
      }.attach()
    } else if (imagesUrlList == null || imagesUrlList.isEmpty()) {
      itemStepBinding.vpRecipeStepsImages.visibility = View.GONE
      itemStepBinding.tabDotIndicator.visibility = View.GONE
    } else if (imagesUrlList.size == 1) {
      itemStepBinding.vpRecipeStepsImages.visibility = View.VISIBLE
      itemStepBinding.tabDotIndicator.visibility = View.GONE
    }
  }
}