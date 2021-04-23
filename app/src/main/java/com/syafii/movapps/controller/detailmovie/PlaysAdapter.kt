/*
 * Created by Muhamad Syafii
 * Friday, 9/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafii.movapps.databinding.ItemPlaysBinding
import com.syafii.movapps.model.Plays

class PlaysAdapter() :
    RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    private lateinit var plays: List<Plays>

    fun setPlaysAdapter(listPlays: List<Plays>) {
        this.plays = listPlays
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemPlaysBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = plays[position]
        holder.bindItem(data)
    }

    override fun getItemCount(): Int = plays.size

    class ViewHolder(private val binding: ItemPlaysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(data: Plays) {
            binding.tvNamePlays.text = data.nama
            Glide.with(binding.root)
                .load(data.url)
                .into(binding.imComingSoon)
        }
    }

}