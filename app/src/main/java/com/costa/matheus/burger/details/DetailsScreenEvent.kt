package com.costa.matheus.burger.details

abstract class DetailsScreenEvent

object OnItemAdded: DetailsScreenEvent()
object OnItemRemoved: DetailsScreenEvent()
object OnItemAddedToOrder: DetailsScreenEvent()
object OnBackPress: DetailsScreenEvent()