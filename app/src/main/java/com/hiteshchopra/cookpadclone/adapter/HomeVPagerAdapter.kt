package com.hiteshchopra.cookpadclone.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
  val homeFragments = mutableListOf<Fragment>()
  override fun getItemCount(): Int = homeFragments.size
  override fun createFragment(position: Int): Fragment = homeFragments[position]
}