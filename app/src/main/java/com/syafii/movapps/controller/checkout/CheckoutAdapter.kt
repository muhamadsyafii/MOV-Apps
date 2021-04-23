/*
 * Created by Muhamad Syafii
 * Thursday, 22/4/2021
 * Copyright (c) 2021.
 * All Rights Reserved
 */

package com.syafii.movapps.controller.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syafii.movapps.databinding.ItemPriceSeatBinding
import com.syafii.movapps.model.Checkout
import com.syafii.movapps.util.currency

class CheckoutAdapter(private val dataList: ArrayList<Checkout> = ArrayList()) :
    RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPriceSeatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
    }


    override fun getItemCount(): Int = dataList.size

    class ViewHolder(private val binding : ItemPriceSeatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: Checkout) {

            currency(data.harga.toDouble(), binding.tvPrice)

            if (data.kursi.startsWith("Total")) {
                binding.tvSeat.text = data.kursi
                binding.tvSeat.setCompoundDrawables(null, null, null, null)
            } else {
                binding.tvSeat.text = "Seat No ${data.kursi}"
            }

        }
    }

}