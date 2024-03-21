package com.chapter3.foodapp.presentation.cataloglist.adapter

import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chapter3.foodapp.base.CatalogViewHolderBinder
import com.chapter3.foodapp.data.model.Catalog
import com.chapter3.foodapp.data.model.Category
import com.chapter3.foodapp.databinding.ItemCatalogGridBinding
import com.chapter3.foodapp.utils.indonesianCurrency

class CatalogGridItemViewHolder (
    private val binding : ItemCatalogGridBinding,
    private val listener : OnItemClickedListener<Catalog>
) : ViewHolder(binding.root), CatalogViewHolderBinder<Catalog> {

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