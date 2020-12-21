package com.adityaprakash.covidinfo.adapters

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

class ContinentAdapter(val continentList: List<ContinentData>) : RecyclerView.Adapter<ContinentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentViewHolder {
           val layoutInflater:LayoutInflater = LayoutInflater.from(parent.context)
           val listItem = layoutInflater.inflate(R.layout.continent_list_layout,parent,false)
           return ContinentViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: ContinentViewHolder, position: Int) {
            val continent:ContinentData = continentList[position]
             holder.bindUI(continent)
    }

    override fun getItemCount(): Int {
          return continentList.size
    }
}

class ContinentViewHolder(val view: View) : RecyclerView.ViewHolder(view){
           fun bindUI(continent:ContinentData){
                view.name.text = continent.continent
                view.active.text = "Active: "+continent.active
                view.total.text = "Total: "+continent.cases
                view.setOnClickListener{
                    val bundle:Bundle = bundleOf("continent_name" to continent.continent)
                    it.findNavController().navigate(R.id.action_nav_continent_to_singleContinentFragment,bundle)
                }


           }
}