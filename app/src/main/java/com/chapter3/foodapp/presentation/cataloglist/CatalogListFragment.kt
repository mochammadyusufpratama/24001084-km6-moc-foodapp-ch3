package com.chapter3.foodapp.presentation.cataloglist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.chapter3.foodapp.R
import com.chapter3.foodapp.data.datasource.CatalogDataSource
import com.chapter3.foodapp.data.datasource.CatalogDataSourceImpl
import com.chapter3.foodapp.data.model.Catalog
import com.chapter3.foodapp.databinding.FragmentCatalogListBinding
import com.chapter3.foodapp.presentation.catalogdetail.DetailActivity
import com.chapter3.foodapp.presentation.cataloglist.adapter.CatalogAdapter
import com.chapter3.foodapp.presentation.cataloglist.adapter.OnItemClickedListener

class CatalogListFragment : Fragment() {

    private lateinit var binding: FragmentCatalogListBinding
    private var adapter: CatalogAdapter? = null
    private val dataSource: CatalogDataSource by lazy { CatalogDataSourceImpl() }
    private var isUsingGridMode: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindCatalogList(isUsingGridMode)
        setClickAction()
    }

    private fun setClickAction() {

        binding.btnListToGrid.setOnClickListener {
            isUsingGridMode = !isUsingGridMode
            setButtonImage(isUsingGridMode)
            bindCatalogList(isUsingGridMode)
        }

    }

    private fun setButtonImage(usingGridMode: Boolean) {
        binding.btnListToGrid.setImageResource(if (usingGridMode) R.drawable.ic_grid else R.drawable.ic_list)
    }

    private fun bindCatalogList(isUsingGrid: Boolean) {

        val listMode = if (isUsingGrid) CatalogAdapter.MODE_GRID else CatalogAdapter.MODE_LIST

        adapter = CatalogAdapter(
            listMode = listMode,
            listener = object : OnItemClickedListener<Catalog> {
                override fun onItemClicked(item: Catalog) {
                    //navigate to detail
                    navigateToDetail(item)
                }
            })

        binding.layoutCatalog.apply {
            adapter = this@CatalogListFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), if (isUsingGrid) 2 else 1)
        }

        adapter?.submitData(dataSource.catalogData())

    }

    private fun navigateToDetail(item: Catalog) {
        val navController = findNavController()
        val bundle = bundleOf(Pair(DetailActivity.EXTRAS_ITEM, item))
        navController.navigate(R.id.action_catalogListFragment_to_detailActivity, bundle)
    }

}