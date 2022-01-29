package com.hiteshchopra.cookpadclone.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hiteshchopra.cookpadclone.R

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
  protected lateinit var binding: B

  @LayoutRes
  protected abstract fun layoutId(): Int

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
    return binding.root
  }

  fun setupToolbar(
    toolbar: Toolbar,
    title: String,
    displayBackButton: Boolean
  ) {
    if (activity is AppCompatActivity) {
      (activity as AppCompatActivity).setSupportActionBar(toolbar)
      (activity as AppCompatActivity).supportActionBar?.let {
        it.setDisplayHomeAsUpEnabled(displayBackButton)
        it.title = title
        if (displayBackButton) {
          toolbar.setNavigationOnClickListener {
            onNavigateUp()
          }
          toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_back)
        }
      }
    }
  }

  fun onNavigateUp() {
    try {
      findNavController().popBackStack()
    } catch (illegalStateException: IllegalStateException) {
      if (activity != null) {
        requireActivity().onBackPressed()
      }
    }
  }

}