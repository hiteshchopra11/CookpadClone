package com.hiteshchopra.domain.mapper

open class DomainModel

open class UIModel

interface UiModelMapper<DoM : DomainModel, UM : UIModel> {
  fun mapToPresentation(domain: DoM): UIModel

  fun mapToDomain(ui: UM): DomainModel
}