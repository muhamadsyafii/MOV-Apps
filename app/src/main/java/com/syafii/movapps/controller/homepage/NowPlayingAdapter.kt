/*
 * Created by Muhamad Syafii
 * Thursday, 8/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafii.movapps.databinding.ItemNowPlayingBinding
import com.syafii.movapps.model.Film
import com.syafii.movapps.util.ItemClickListener

class NowPlayingAdapter(private val itemClickListener: ItemClickListener<Film>) :
    RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    private lateinit var film: List<Film>

    fun setNowPlayingAdapter(lisFilm: List<Film>) {
        this.film = lisFilm
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemNowPlayingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = film[position]
        holder.bindItem(data)
        holder.itemView.setOnClickListener { itemClickListener.onItemClick(data)}
    }

    override fun getItemCount(): Int = film.size


    class ViewHolder(val binding: ItemNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var film: Film

        fun bindItem(film: Film) {
            this.film = film
            binding.tvTitleNameMovie.text = film.judul
            binding.tvCategoryMovie.text = film.genre
            binding.tvRatingMovie.text = film.rating
            Glide.with(binding.root)
                .load(film.poster)
                .into(binding.imNowPlaying)

        }

    }

}
