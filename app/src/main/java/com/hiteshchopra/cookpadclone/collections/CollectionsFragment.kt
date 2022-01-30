package com.hiteshchopra.cookpadclone.collections

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.R.string
import com.hiteshchopra.cookpadclone.base.BaseFragment
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionsAdapter
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionsListener
import com.hiteshchopra.cookpadclone.collections.adapter.ImagesVPAdapter
import com.hiteshchopra.cookpadclone.databinding.FragmentCollectionsBinding
import com.hiteshchopra.cookpadclone.databinding.ItemCollectionsBinding
import com.hiteshchopra.cookpadclone.home.HomeFragmentDirections
import com.hiteshchopra.cookpadclone.models.collection.CollectionsItemUIModel
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.Failure
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.Loading
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.NetworkError
import com.hiteshchopra.cookpadclone.utils.Utils.ViewState.SuccessWithData
import com.hiteshchopra.cookpadclone.utils.Utils.showToast
import com.hiteshchopra.domain.model.CollectionsItemDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CollectionsFragment : BaseFragment<FragmentCollectionsBinding>(), CollectionsListener {

  override fun layoutId(): Int = R.layout.fragment_collections

  private val collectionViewModel: CollectionsFragmentVM by viewModels()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initObservers()
  }

  private fun initObservers() {
    lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collectionViewModel.viewState.collect { viewState ->
          binding.isLoading = viewState is Loading
          when (viewState) {
            is Failure -> showToast(
              requireContext(), viewState.exception?.message ?: getString(
                string.unknown_error
              )
            )
            NetworkError -> showToast(requireContext(), getString(string.network_error))
            is SuccessWithData<*> -> {
              initRecyclerView(collectionViewModel.mapToUi(viewState.data as List<CollectionsItemDomain>))
            }
            else -> {
              // Do Nothing
            }
          }
        }
      }
    }
  }

  private fun initRecyclerView(collectionsItemDomain: List<CollectionsItemUIModel>) {
    val collectionsAdapter = CollectionsAdapter(this, collectionsItemDomain)
    binding.rvCollections.adapter = collectionsAdapter
  }

  override fun populateImages(
    itemCollectionBinding: ItemCollectionsBinding,
    imagesCollection: List<String?>?
  ) {
    val collectionImagesVPAdapter = ImagesVPAdapter(imagesCollection)
    itemCollectionBinding.vpCollectionImages.adapter = collectionImagesVPAdapter
    TabLayoutMediator(
      itemCollectionBinding.tabDotIndicator,
      itemCollectionBinding.vpCollectionImages
    ) { tab, position ->
    }.attach()
  }

  override fun onCollectionItemClicked(collectionsItemUIModel: CollectionsItemUIModel) {
    view?.findNavController()?.navigate(
      HomeFragmentDirections.actionHomeFragmentToCollectionRecipeFragment(
        collectionsItemUIModel.id ?: -1
      )
    )
  }
}