package com.hiteshchopra.cookpadclone.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.hiteshchopra.cookpadclone.HomeFragmentDirections
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.R.string
import com.hiteshchopra.cookpadclone.databinding.FragmentRecipesBinding
import com.hiteshchopra.cookpadclone.utils.ViewState.Failure
import com.hiteshchopra.cookpadclone.utils.ViewState.Loading
import com.hiteshchopra.cookpadclone.utils.ViewState.NetworkError
import com.hiteshchopra.cookpadclone.utils.ViewState.SuccessWithData
import com.hiteshchopra.cookpadclone.utils.showToast
import com.hiteshchopra.domain.model.RecipeItemDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment(), RecipeListener {

  private val recipesFragmentVM: RecipesFragmentVM by viewModels()
  private lateinit var binding: FragmentRecipesBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(
      inflater,
      R.layout.fragment_recipes,
      container,
      false
    )
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initObservers()
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
              initRecyclerView(viewState.data as List<RecipeItemDomain>)
            }
            else -> {
              // Do Nothing
            }
          }
        }
      }
    }
  }

  private fun initRecyclerView(recipeItemDomainList: List<RecipeItemDomain>) {
    val recipesAdapter = RecipesAdapter(this, recipeItemDomainList)
    binding.rvRecipes.adapter = recipesAdapter
  }

  override fun onRecipeClicked(recipeItemDomain: RecipeItemDomain) {
    val directions = HomeFragmentDirections.actionHomeFragmentToRecipeDetailsFragment(recipeItemDomain)
    view?.findNavController()?.navigate(directions)
  }
}