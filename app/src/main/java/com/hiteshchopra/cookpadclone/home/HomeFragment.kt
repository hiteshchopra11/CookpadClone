package com.hiteshchopra.cookpadclone.home

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.hiteshchopra.cookpadclone.R
import com.hiteshchopra.cookpadclone.base.BaseFragment
import com.hiteshchopra.cookpadclone.collections.CollectionsFragment
import com.hiteshchopra.cookpadclone.databinding.FragmentHomeBinding
import com.hiteshchopra.cookpadclone.recipes.RecipesFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  override fun layoutId(): Int = R.layout.fragment_home

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
    TabLayoutMediator(
      binding.tabLayout,
      binding.pager,
      false, false
    ) { tab, position ->
      tab.text = when (position) {
        0 -> "Collections"
        1 -> "Recipes"
        else -> ""
      }
    }.attach()
  }
}