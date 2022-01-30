package com.hiteshchopra.cookpadclone.models.collection

import com.hiteshchopra.domain.mapper.UiModelMapper
import com.hiteshchopra.domain.model.CollectionsItemDomain

class CollectionsItemUIMapper : UiModelMapper<CollectionsItemDomain, CollectionsItemUIModel> {
  override fun mapToDomain(ui: CollectionsItemUIModel): CollectionsItemDomain {
    return CollectionsItemDomain(
      description = ui.description,
      id = ui.id,
      previewImageUrls = ui.previewImageUrls,
      recipeCount = ui.recipeCount,
      title = ui.title
    )
  }

  override fun mapToPresentation(domain: CollectionsItemDomain): CollectionsItemUIModel {
    return CollectionsItemUIModel(
      description = domain.description,
      id = domain.id,
      previewImageUrls = domain.previewImageUrls,
      recipeCount = domain.recipeCount,
      title = domain.title
    )
  }
}