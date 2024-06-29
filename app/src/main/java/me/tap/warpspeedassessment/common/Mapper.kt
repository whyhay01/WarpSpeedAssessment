package me.tap.warpspeedassessment.common

interface Mapper<Element, Data> {

    fun from(cache: Element): Data

    fun to(data: Data): Element

    fun mapModelList(models: List<Element>): List<Data> {
        return models.mapTo(mutableListOf(), ::from)
    }

    fun mapEntityList(entityList: List<Data>): List<Element> {
        return entityList.mapTo(mutableListOf(), this::to)
    }
}