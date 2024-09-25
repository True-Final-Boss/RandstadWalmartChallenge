package com.example.walmartchallenge.ui.countries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartchallenge.R
import com.example.walmartchallenge.data.model.CountriesModel
import com.example.walmartchallenge.data.model.Country
import com.example.walmartchallenge.databinding.ItemCountriesBinding

class CountriesAdapter(var countries: CountriesModel) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    var onItemClick: ((Country) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCountriesBinding.bind(view)
        fun handleData(item: Country?) {
            item?.let {
                binding.tvName.text = item.name//+item.region+" "+item.code+"|"
                binding.tvRegion.text = item.region
                binding.tvCode.text = item.code
                binding.tvCapital.text = item.capital
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.handleData(countries?.get(position))
        holder.itemView.setOnClickListener {
            countries?.get(position)?.let {
                onItemClick?.invoke(it)
            }
        }
    }

    override fun getItemCount(): Int = countries.size ?: 0

}