package com.hiteshchopra.cookpadclone.recipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.R.string
import com.hiteshchopra.cookpadclone.base.BaseFragment
import com.hiteshchopra.cookpadclone.databinding.FragmentRecipesBinding
import com.hiteshchopra.cookpadclone.home.HomeFragmentDirections
import com.hiteshchopra.cookpadclone.models.recipe.RecipeItemUIModel
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.Failure
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.Loading
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.NetworkError
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.SuccessWithData
import com.hiteshchopra.cookpadclone.utils.Utils.showToast
import com.hiteshchopra.domain.model.RecipeItemDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : BaseFragment<FragmentRecipesBinding>(), RecipeListener {
  override fun layoutId(): Int = R.layout.fragment_recipes
  private val recipesFragmentVM: RecipesFragmentVM by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initObservers()
    binding.toolbar.visibility = View.GONE
  }

  private fun initObservers() {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        recipesFragmentVM.viewState.collect { viewState ->
          binding.isLoading = viewState is Loading
          when (viewState) {
            is Failure -> showToast(
              requireContext(), viewState.exception?.message ?: getString(
                string.unknown_error
              )
            )
            NetworkError -> showToast(requireContext(), getString(string.network_error))
            is SuccessWithData<*> -> {
              initRecyclerView(recipesFragmentVM.mapToUi(viewState.data as List<RecipeItemDomain>))
            }
            else -> {
              // Do Nothing
            }
          }
        }
      }
    }
  }

  private fun initRecyclerView(recipeItemUIModelList: List<RecipeItemUIModel>) {
    val recipesAdapter = RecipesAdapter(this, recipeItemUIModelList)
    binding.rvRecipes.adapter = recipesAdapter
  }

  override fun onRecipeClicked(recipeItemUIModel: RecipeItemUIModel) {
    view?.findNavController()?.navigate(
      HomeFragmentDirections.actionHomeFragmentToRecipeDetailsFragment(
        recipeItemUIModel
      )
    )
  }
}