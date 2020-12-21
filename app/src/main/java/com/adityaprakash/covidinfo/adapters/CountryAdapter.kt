package com.adityaprakash.covidinfo.adapters

import com.adityaprakash.covidinfo.data.vo.CountryData


import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adityaprakash.covidinfo.R
import com.adityaprakash.covidinfo.data.vo.ContinentData
import kotlinx.android.synthetic.main.continent_list_layout.view.*

class CountryAdapter(val countryList: List<CountryData>) : RecyclerView.Adapter<CountryViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.continent_list_layout,parent,false)
        return CountryViewHolder(listItem)
    }



    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country:CountryData = countryList[position]
        holder.bindUI(country)
    }
}

class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun bindUI(country:CountryData){
        view.name.text = country.country
        view.active.text = "Active: "+country.active
        view.total.text = "Total: "+country.cases
        view.setOnClickListener{
            val bundle:Bundle = bundleOf("country_name" to country.country)
            it.findNavController().navigate(R.id.action_nav_country_to_singleCountryFragment,bundle)
        }


    }
}