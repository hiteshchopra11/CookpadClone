package com.hiteshchopra.cookpadclone

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hiteshchopra.cookpadclone.adapter.HomePagerAdapter
import com.hiteshchopra.cookpadclone.collections.CollectionsFragment
import com.hiteshchopra.cookpadclone.databinding.ActivityHomeBinding
import com.hiteshchopra.cookpadclone.recipes.RecipesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : FragmentActivity() {

  private lateinit var binding: ActivityHomeBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
  }
}