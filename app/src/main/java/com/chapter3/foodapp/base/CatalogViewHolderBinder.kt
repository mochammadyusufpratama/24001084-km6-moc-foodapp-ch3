package com.chapter3.foodapp.base

import com.chapter3.foodapp.data.model.Catalog

interface CatalogViewHolderBinder<T> { fun bind(item: Catalog) }