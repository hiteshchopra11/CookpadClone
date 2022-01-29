package com.hiteshchopra.cookpadclone.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
  val homeFragments = mutableListOf<Fragment>()
  override fun getItemCount(): Int = homeFragments.size
  override fun createFragment(position: Int): Fragment = homeFragments[position]
}