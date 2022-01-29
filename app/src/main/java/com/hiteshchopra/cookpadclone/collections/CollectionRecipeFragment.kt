package com.hiteshchopra.cookpadclone.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.R.string
import com.hiteshchopra.cookpadclone.base.BaseFragment
import com.hiteshchopra.cookpadclone.databinding.FragmentRecipesBinding
import com.hiteshchopra.cookpadclone.recipes.RecipeListener
import com.hiteshchopra.cookpadclone.recipes.RecipesAdapter
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.Failure
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.Loading
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.NetworkError
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.SuccessWithData
import com.hiteshchopra.cookpadclone.utils.Utils.showToast
import com.hiteshchopra.domain.model.RecipeItemDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CollectionRecipeFragment : BaseFragment<FragmentRecipesBinding>(), RecipeListener {

  override fun layoutId(): Int = R.layout.fragment_recipes

  private val collectionRecipeFragmentArgs: CollectionRecipeFragmentArgs by navArgs()
  private val collectionRecipeFragmentVM: CollectionRecipeFragmentVM by viewModels()

  private lateinit var recipesAdapter: RecipesAdapter
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
    initUI()
  }

  private fun initUI() {
    binding.toolbar.visibility = View.VISIBLE

    /* Setup the toolbar */
    setupToolbar(binding.toolbar, getString(R.string.recipes), true)

    /* To avoid making additional API call when returning back from the details screen.
    * If the adapter is already initialised, it means the adapter was already set after successfully
    * receiving the data from the server. This would not be used if the action we perform later in
    * the app, could affect the list of recipes(for example editing or deleting any recipe). But in
    * this case, even if we an API call, we will still receive list. ListAdapter can also be used for
    * this scenario which uses DiffUtil internally to calculate difference between 2 lists, but that would
    * be a costly operation. */
    if (!::recipesAdapter.isInitialized) {
      collectionRecipeFragmentVM.getRecipesByCollectionID(collectionRecipeFragmentArgs.collectionId)
    }

  }

  private fun initObservers() {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collectionRecipeFragmentVM.viewState.collect { viewState ->
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
    recipesAdapter = RecipesAdapter(this, recipeItemDomainList)
    binding.rvRecipes.adapter = recipesAdapter
  }

  override fun onRecipeClicked(recipeItemDomain: RecipeItemDomain) {
    view?.findNavController()?.navigate(
      CollectionRecipeFragmentDirections.actionCollectionRecipeFragmentToRecipeDetailsFragment(
        recipeItemDomain
      )
    )
  }
}