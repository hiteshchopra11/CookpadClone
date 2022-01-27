package com.hiteshchopra.data.remote.model.collection

import com.hiteshchopra.data.remote.mapper.EntityMapper
import com.hiteshchopra.domain.model.CollectionsItemDomain

class CollectionsItemDataMapper : EntityMapper<CollectionsItemDomain, CollectionsItemData> {
  override fun mapToDomain(data: CollectionsItemData): CollectionsItemDomain {
    return CollectionsItemDomain(
      description = data.description,
      id = data.id,
      previewImageUrls = data.previewImageUrls,
      recipeCount = data.recipeCount,
      title = data.title
    )
  }

  override fun mapToData(domain: CollectionsItemDomain): CollectionsItemData {
    return CollectionsItemData(
      description = domain.description,
      id = domain.id,
      previewImageUrls = domain.previewImageUrls,
      recipeCount = domain.recipeCount,
      title = domain.title
    )
  }
}