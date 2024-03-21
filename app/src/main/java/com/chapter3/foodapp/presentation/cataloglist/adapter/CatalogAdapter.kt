package com.chapter3.foodapp.presentation.cataloglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chapter3.foodapp.base.CatalogViewHolderBinder
import com.chapter3.foodapp.data.model.Catalog
import com.chapter3.foodapp.databinding.ItemCatalogGridBinding
import com.chapter3.foodapp.databinding.ItemCatalogListBinding

interface OnItemClickedListener<T> {
    fun onItemClicked(item: T)
}

class CatalogAdapter (
    private val listener: OnItemClickedListener<Catalog>,
    private val listMode: Int = MODE_LIST
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val MODE_LIST = 0
        const val MODE_GRID = 1
    }

    private var asyncDataDiffer = AsyncListDiffer(

        this, object : DiffUtil.ItemCallback<Catalog>() {

            override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }

    )

    fun submitData(data: List<Catalog>) {
        asyncDataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (listMode == MODE_GRID) CatalogGridItemViewHolder(
            ItemCatalogGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        ) else {
            CatalogListItemViewHolder(
                ItemCatalogListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listener
            )
        }

    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is CatalogViewHolderBinder<*>) return
        (holder as CatalogViewHolderBinder<Catalog>).bind(asyncDataDiffer.currentList[position])
    }

}