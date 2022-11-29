package com.malhotra.retrofitpractice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.malhotra.retrofitpractice.databinding.RvItemBinding
import com.malhotra.retrofitpractice.network.Character

class Adapter(private val context: Context, private val list : List<Character>)
    :RecyclerView.Adapter<Adapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context)
            .inflate(R.layout.rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = list[position]
        holder.binding.name.text = character.name
        holder.binding.image.load(character.image){
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = RvItemBinding.bind(view)
    }

}