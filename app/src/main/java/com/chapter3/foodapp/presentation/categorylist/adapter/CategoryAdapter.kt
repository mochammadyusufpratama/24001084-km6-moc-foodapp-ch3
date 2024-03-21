package com.chapter3.foodapp.presentation.categorylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chapter3.foodapp.data.model.Category
import com.chapter3.foodapp.databinding.ItemCategoryListBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val data = mutableListOf<Category>()
    fun submitData(items: List<Category>) {
        data.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        return CategoryViewHolder(
            ItemCategoryListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class CategoryViewHolder(private val binding : ItemCategoryListBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category) {
            binding.ivImageCategory.setImageResource(item.image)
            binding.tvCategoryName.text = item.name
        }

    }

}