package com.hiteshchopra.cookpadclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hiteshchopra.cookpadclone.adapter.HomePagerAdapter
import com.hiteshchopra.cookpadclone.collections.CollectionsFragment
import com.hiteshchopra.cookpadclone.databinding.FragmentHomeBinding
import com.hiteshchopra.cookpadclone.recipes.RecipesFragment

class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(
      inflater,
      R.layout.fragment_home,
      container,
      false
    )
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUI()
  }

  private fun initUI() {
    val pagerAdapter = HomePagerAdapter(this)
    val homeFragmentList = listOf(CollectionsFragment(), RecipesFragment())
    pagerAdapter.homeFragments.addAll(homeFragmentList)
    binding.pager.adapter = pagerAdapter
    binding.pager.currentItem = 0
    TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
      tab.text = when (position) {
        0 -> "Collections"
        1 -> "Recipes"
        else -> ""
      }
    }.attach()
  }
}