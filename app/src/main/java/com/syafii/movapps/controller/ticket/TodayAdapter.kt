/*
 * Created by Muhamad Syafii
 * Thursday, 22/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.ticket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafii.movapps.databinding.ItemComingSoonBinding
import com.syafii.movapps.model.Film
import com.syafii.movapps.util.ItemClickListener

class TodayAdapter(private val itemClickListener: ItemClickListener<Film>) :
    RecyclerView.Adapter<TodayAdapter.ViewHolder>() {

    private lateinit var film: List<Film>

    fun setTodayAdapter(listFilm: List<Film>) {
        this.film = listFilm
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemComingSoonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = film[position]
        holder.bindItem(data)
        holder.itemView.setOnClickListener { itemClickListener.onItemClick(data) }
    }

    override fun getItemCount(): Int = film.size

    inner class ViewHolder(private val binding: ItemComingSoonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(film: Film) {
            binding.tvTitleNameMovie.text = film.judul
            binding.tvCategoryMovie.text = film.genre
            binding.tvRatingMovie.text = film.rating
            Glide.with(binding.root)
                .load(film.poster)
                .into(binding.imComingSoon)
        }
    }
}
