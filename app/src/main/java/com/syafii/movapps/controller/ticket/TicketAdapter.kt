/*
 * Created by Muhamad Syafii
 * Monday, 26/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.ticket

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.syafii.movapps.R
import com.syafii.movapps.databinding.ItemPriceSeatBinding
import com.syafii.movapps.model.Checkout

class TicketAdapter(private val context: Context) :
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    private lateinit var checkout: List<Checkout>

    fun setTicketAdapter(listCheckout: List<Checkout>) {
        this.checkout = listCheckout
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPriceSeatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = checkout[position]
        holder.bindItem(data)
    }

    override fun getItemCount(): Int = checkout.size

    inner class ViewHolder(private val binding: ItemPriceSeatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(checkout: Checkout) {
            binding.tvSeat.setTextColor(ContextCompat.getColor(context, R.color.colorBlue))
            binding.tvSeat.text = checkout.kursi
            binding.tvPrice.visibility = View.GONE

            if (checkout.kursi.startsWith("Total")) {
                binding.tvSeat.visibility = View.GONE
            } else {
                binding.tvSeat.text = "Seat No ${checkout.kursi}"
            }
        }
    }
}
