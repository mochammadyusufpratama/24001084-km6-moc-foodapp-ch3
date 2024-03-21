package com.chapter3.foodapp.presentation.catalogdetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chapter3.foodapp.data.model.Catalog
import com.chapter3.foodapp.databinding.ActivityDetailBinding
import com.chapter3.foodapp.databinding.ActivityMainBinding
import com.chapter3.foodapp.utils.indonesianCurrency

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getArgumentData()
        backButton()

        if (binding.tvNominal.text.toString().toInt() == 1) {
            binding.ivMinusBtn.alpha = 0.3f
        }

    }

    private fun getArgumentData() {

        intent?.extras?.getParcelable<Catalog>(CatalogDetailFragment.EXTRAS_ITEM)?.let {
            setCatalogImage(it.image)
            setCatalogData(it)
            setClickCatalogLocation(it.locationUrl)
            setTotalPrice(it.price)
        }

    }

    private fun setCatalogImage(imgResDrawable: Int?) {

        imgResDrawable?.let {
            binding.ivImageCatalog.setImageResource(it)
        }

    }

    private fun setCatalogData(catalog: Catalog) {

        binding.tvCatalogName.text = catalog.name
        binding.tvCatalogPrice.text = catalog.price.indonesianCurrency()
        binding.tvCatalogDesc.text = catalog.desc
        binding.tvCatalogLocation.text = catalog.location

    }

    private fun setClickCatalogLocation(catalogLocation : String?) {

        binding.tvCatalogLocation.setOnClickListener {

            val url = Uri.parse(catalogLocation)
            val mapIntent = Intent(Intent.ACTION_VIEW, url)
            startActivity(mapIntent)

        }

    }

    private fun setTotalPrice(catalogPrice: Double) {

        binding.ivPlusBtn.setOnClickListener {

            var nominal = binding.tvNominal.text.toString().toInt()
            var total : Double

            if (nominal > 0) {

                binding.ivMinusBtn.alpha = 1.0f

                nominal++
                total = catalogPrice * nominal

                binding.tvNominal.text = nominal.toString()
                binding.tvBtnText.text = "Tambah ke Keranjang - " + total.indonesianCurrency()

            }
        }

        binding.ivMinusBtn.setOnClickListener {

            var nominal = binding.tvNominal.text.toString().toInt()
            var total : Double

            if (nominal == 2) {
                binding.ivMinusBtn.alpha = 0.3f
            }

            if (nominal > 1) {

                nominal--
                total = catalogPrice * nominal

                binding.tvNominal.text = nominal.toString()
                binding.tvBtnText.text = "Tambah ke Keranjang - " + total.indonesianCurrency()

            }

        }

    }

    private fun backButton() {

        binding.ivBackBtn.setOnClickListener {
            finish()
        }

    }

    companion object{

        const val EXTRAS_ITEM = "EXTRAS_ITEM"

        fun newIntent(context: Context, catalog: Catalog): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRAS_ITEM, catalog)
            return intent
        }

    }

}
