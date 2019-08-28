package com.chsltutorials.dogbreeds.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chsltutorials.dogbreeds.R
import com.chsltutorials.dogbreeds.model.entity.Breed
import kotlinx.android.synthetic.main.item_breed_adapter.view.*


interface OnItemClickListener {  fun onItemClick(item : Breed) }


class BreedAdapter(private var itemList: List<Breed>, private var listener: OnItemClickListener) : RecyclerView.Adapter<BreedAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_breed_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position],listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(breedName: Breed, listener: OnItemClickListener) {
            itemView.tvBreedName.text = breedName.nome
            itemView.llItemBreed.setOnClickListener {
                listener.onItemClick(breedName)
            }
        }
    }

}


