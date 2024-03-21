package com.chapter3.foodapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chapter3.foodapp.R
import com.chapter3.foodapp.data.model.Category
import com.chapter3.foodapp.databinding.ActivityMainBinding
import com.chapter3.foodapp.presentation.categorylist.adapter.CategoryAdapter

class MainActivity : AppCompatActivity() {

    private val categoryAdapter = CategoryAdapter()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListCategory()
    }

    private fun setListCategory() {

        val data = listOf(
            Category(image = R.drawable.img_food, name = "Food"),
            Category(image = R.drawable.img_drink, name = "Drink"),
            Category(image = R.drawable.img_snack, name = "Snack"),
            Category(image = R.drawable.bg_banner, name = "Fruit"),
            Category(image = R.drawable.img_vagetables, name = "Vagetables")
        )

        binding.layoutCategory.apply {
            adapter = this@MainActivity.categoryAdapter
        }

        categoryAdapter.submitData(data)

    }

}