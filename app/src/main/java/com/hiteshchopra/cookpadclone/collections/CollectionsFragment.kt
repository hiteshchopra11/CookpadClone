package com.hiteshchopra.cookpadclone.collections

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
import com.google.android.material.tabs.TabLayoutMediator
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.R.string
import com.hiteshchopra.cookpadclone.collections.ViewState.Failure
import com.hiteshchopra.cookpadclone.collections.ViewState.Loading
import com.hiteshchopra.cookpadclone.collections.ViewState.NetworkError
import com.hiteshchopra.cookpadclone.collections.ViewState.SuccessWithData
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionImagesVPAdapter
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionsAdapter
import com.hiteshchopra.cookpadclone.collections.adapter.CollectionsListener
import com.hiteshchopra.cookpadclone.databinding.FragmentCollectionsBinding
import com.hiteshchopra.cookpadclone.databinding.ItemCollectionsBinding
import com.hiteshchopra.cookpadclone.utils.showToast
import com.hiteshchopra.domain.model.CollectionsItemDomain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CollectionsFragment : Fragment(), CollectionsListener {

  private val collectionViewModel: CollectionsFragmentVM by viewModels()
  private lateinit var binding: FragmentCollectionsBinding
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(
      inflater,
      R.layout.fragment_collections,
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
              initRecyclerView(viewState.data as List<CollectionsItemDomain>)
            }
            else -> {
              // Do Nothing
            }
          }
        }
      }
    }
  }

  private fun initRecyclerView(collectionsItemDomain: List<CollectionsItemDomain>) {
    val collectionsAdapter = CollectionsAdapter(this, collectionsItemDomain)
    binding.rvCollections.adapter = collectionsAdapter
  }

  override fun populateImages(
    itemCollectionBinding: ItemCollectionsBinding,
    imagesCollection: List<String?>?
  ) {
    val collectionImagesVPAdapter = CollectionImagesVPAdapter(imagesCollection)
    itemCollectionBinding.vpCollectionImages.adapter = collectionImagesVPAdapter
    TabLayoutMediator(
      itemCollectionBinding.tabDotIndicator,
      itemCollectionBinding.vpCollectionImages
    ) { tab, position ->
    }.attach()
  }

  override fun onCollectionItemClicked(collectionsItemDomain: CollectionsItemDomain) {
    // Navigate to recipes screen
  }
}