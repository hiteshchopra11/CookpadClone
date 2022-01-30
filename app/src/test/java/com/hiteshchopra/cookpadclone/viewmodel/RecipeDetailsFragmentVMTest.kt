package com.hiteshchopra.cookpadclone.viewmodel

import com.hiteshchopra.cookpadclone.recipes.details.RecipeDetailsFragmentVM
import org.junit.Before
import org.junit.Test

class RecipeDetailsFragmentVMTest {
  private lateinit var viewModel: RecipeDetailsFragmentVM

  @Before
  fun setup() {
    viewModel = RecipeDetailsFragmentVM()
  }

  @Test
  fun `verify format date converts to the correct format`() {
    val origDate = "2021-10-20T03:36:45+00:0"
    val output = viewModel.formatDate(origDate)
    val expectedDate = "20 October 2021"
    assert(output == expectedDate)
  }

  @Test
  fun `verify empty date returns empty response`() {
    val origDate = ""
    val output = viewModel.formatDate(origDate)
    val emptyDate = ""
    assert(output == emptyDate)
  }

  @Test
  fun `verify invalid date is handled gracefully without crashing and does not match`() {
    val origDate = "2021-99-99T03:36:45+00:0"
    val output = viewModel.formatDate(origDate)
    val incorrectDate = "12 October 1999"
    assert(output != incorrectDate)
  }

  @Test
  fun `verify incorrect date format is handled gracefully without crashing and does not match`() {
    val origDate = "2021-99-99T03:36:45+00:0"
    val output = viewModel.formatDate(origDate)
    val incorrectDate = "12 October 1999"
    assert(output != incorrectDate)
  }
}