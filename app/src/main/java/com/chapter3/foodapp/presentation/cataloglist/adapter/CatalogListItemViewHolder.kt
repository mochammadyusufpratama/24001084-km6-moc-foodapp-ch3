package com.chapter3.foodapp.presentation.cataloglist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.chapter3.foodapp.base.CatalogViewHolderBinder
import com.chapter3.foodapp.data.model.Catalog
import com.chapter3.foodapp.databinding.ItemCatalogListBinding
import com.chapter3.foodapp.utils.indonesianCurrency

class CatalogListItemViewHolder (
    private val binding : ItemCatalogListBinding,
    private val listener : OnItemClickedListener<Catalog>
) : RecyclerView.ViewHolder(binding.root), CatalogViewHolderBinder<Catalog> {

    override fun bind(item: Catalog) {

        item.let {

            binding.ivImageCatalog.setImageResource(it.image)
            binding.tvCatalogName.text = it.name
            binding.tvCatalogPrice.text = it.price.indonesianCurrency()

            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }

        }

    }

}